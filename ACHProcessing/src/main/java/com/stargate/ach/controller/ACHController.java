package com.stargate.ach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.stargate.ach.business.entity.BLTransaction;
import com.stargate.ach.entity.PersistResponseStatus;
import com.stargate.ach.entity.ResponseStatus;
import com.stargate.ach.entity.TransferRequest;
import com.stargate.ach.exception.MulitpleDBRowEffectedException;
import com.stargate.ach.logging.BaseLogger;
import com.stargate.ach.service.ACHService;

@RestController
@RequestMapping("ach")
public class ACHController {

	@Autowired
	private ACHService service;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private BaseLogger persistLogger;

	@Autowired
	@Qualifier("debitTransferRequest")
	private TransferRequest debitTransferRequest;

	@Autowired
	@Qualifier("creditTransferRequest")
	private TransferRequest creditTransferRequest;

	@Value("${stargate.userstory2.ipaddress}")
	String address;

	/***
	 * Builds the Uri for accessing the `executeTransfer` towards US-2 endpoints
	 ***/
	private String UriBuilder(String accountNo) {
		String url = address + "/transferfunds/" + accountNo + "/executeTransfer";
		return url;
	}

	/***
	 * This method will receive input from the Mule Flow after the scatter-gather
	 * segregates the list of transactions. Each transaction will be sent to this
	 * endpoint through RESTful webservice from Mule
	 ****/
	@PostMapping("/persist")
	public ResponseEntity<ResponseStatus> persistTransfer(@RequestBody BLTransaction txn) {

		PersistResponseStatus respStatus = new PersistResponseStatus("SUCCESS : Data Persisted", "");

		if (txn == null) {
			respStatus.setStatus("FAIL");
			respStatus.setError("Null payload received");
			persistLogger.appendMessages("The BLTransaction object received is null");
			return new ResponseEntity<ResponseStatus>(respStatus, HttpStatus.ACCEPTED);
		}

		// save it in the transaction table
		service.addTransaction(txn);
		int sts = service.updateStatus(txn, txn.getStatus());
		respStatus.setPersistId(txn.getTransactionId());
		if (sts > 1) {
			respStatus.setStatus("FAIL");
			respStatus.setError(MulitpleDBRowEffectedException.getMesg());
			persistLogger
					.appendMessages("While persisting data multiple rows were effected. This is not a desired result");
		}

		if (sts <= 0) {
			respStatus.setStatus("FAIL");
			respStatus.setError("Data Persist Failed");
			persistLogger.appendMessages("The data failed to persist due to some unexpected errors");
		}

		persistLogger.writeLogs();
		return new ResponseEntity<ResponseStatus>(respStatus, HttpStatus.ACCEPTED);

		// ResponseEntity<ResponseStatus> newResponseEntity = new
		// ResponseEntity<ResponseStatus>(HttpStatus.ACCEPTED); // JUST THE TESTING
		// ResponseStatus responseStatus = new ResponseStatus("SUCCESS",""); // JUST THE
		// TESTING
	}

	@PostMapping("/deliver-to-sender")
	public ResponseEntity<ResponseStatus> deliverRequestToSenderTransfer(@RequestBody BLTransaction txn) {

		// create a new TransferRequest POJO from the Transaction entity for the sender.
		// TODO we have assumed here that the sender is always DEBIT
		debitTransferRequest.buildDebitTransferRequest(txn);

		persistLogger.appendMessages("Received BLTransaction object is: "+txn.toString());
		
		// URL to send the request to SENDER Bank
		String url = UriBuilder(debitTransferRequest.getAccountNo());
		ResponseEntity<TransferRequest> entity = new ResponseEntity<TransferRequest>(debitTransferRequest,
				HttpStatus.ACCEPTED);

		persistLogger.appendMessages("Calling the endpoints that will " + debitTransferRequest.getTransactionType()
				+ " the " + entity.getBody().getAccountNo() + " with amount $" + debitTransferRequest.getAmount());

		ResponseEntity<ResponseStatus> newResponseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
				ResponseStatus.class);

		ResponseStatus responseStatus = (ResponseStatus) newResponseEntity.getBody();

		persistLogger.appendMessages("Received the response from endpoint with body " + responseStatus.getStatus() + " "
				+ responseStatus.getError());

		// handling the case where the response received is FAIL
		if (responseStatus.getStatus().equals("FAIL")) {

			// update only if the status is FAIL, that is the only reason the status will
			// change. Default status is `InProgress`
			int sts = service.updateStatus(txn, "FAIL to " + debitTransferRequest.getTransactionType() + " sender");
			if (sts > 1) {
				responseStatus.setError(responseStatus.getError() + "\n" + MulitpleDBRowEffectedException.getMesg());
				persistLogger.appendMessages(
						"While persisting data multiple rows were effected. This is not a desired result");
			}

			if (sts <= 0) {
				responseStatus.setError(responseStatus.getError() + "\n" + "Data Persist Failed");
				persistLogger.appendMessages("The status change failed to persist due to some unexpected errors");
			}

			persistLogger.appendMessages("Updated the transaction status with result as " + sts);
		}

		persistLogger.writeLogs();
		return new ResponseEntity<ResponseStatus>(responseStatus, HttpStatus.ACCEPTED);
	}

	@PostMapping("/deliver-to-receiver")
	public ResponseEntity<ResponseStatus> deliverRequestToReceiverTransfer(@RequestBody BLTransaction txn) {

		// create a new TransferRequest POJO from the Transaction entity for the
		// receiver.
		// TODO we have assumed here that the receiver is always CREDIT
		creditTransferRequest.buildCreditTransferRequest(txn);
		// URL to send the request to RECEIVER Bank
		String url = UriBuilder(creditTransferRequest.getAccountNo());

		ResponseEntity<TransferRequest> entity = new ResponseEntity<TransferRequest>(creditTransferRequest,
				HttpStatus.ACCEPTED);

		persistLogger.appendMessages("Calling the endpoints that will " + creditTransferRequest.getTransactionType()
				+ " the " + entity.getBody().getAccountNo() + " with amount $" + creditTransferRequest.getAmount());

		ResponseEntity<ResponseStatus> newResponseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
				ResponseStatus.class);

		ResponseStatus responseStatus = (ResponseStatus) newResponseEntity.getBody();

		persistLogger.appendMessages("Received the response from endpoint with body " + responseStatus.getStatus() + " "
				+ responseStatus.getError());

		// handling the case where the response received is FAIL
		if (responseStatus.getStatus().equals("FAIL")) {

			// update only if the status is FAIL, that is the only reason the status will
			// change. Default status is `InProgress`
			int sts = service.updateStatus(txn, "FAIL to " + creditTransferRequest.getTransactionType() + " receiver");
			if (sts > 1) {
				responseStatus.setError(responseStatus.getError() + "\n" + MulitpleDBRowEffectedException.getMesg());
				persistLogger.appendMessages(
						"While persisting data multiple rows were effected. This is not a desired result");
			}

			if (sts <= 0) {
				responseStatus.setError(responseStatus.getError() + "\n" + "Data Persist Failed");
				persistLogger.appendMessages("The data failed to persist due to some unexpected errors");
			}

			persistLogger.appendMessages("Updated the transaction status with result as " + sts);
		}
		
		persistLogger.writeLogs();
		return new ResponseEntity<ResponseStatus>(responseStatus, HttpStatus.ACCEPTED);
	}
}
