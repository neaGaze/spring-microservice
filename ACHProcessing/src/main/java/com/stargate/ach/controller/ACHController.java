package com.stargate.ach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.stargate.ach.business.entity.BLTransaction;
import com.stargate.ach.entity.PersistResponseStatus;
import com.stargate.ach.entity.ResponseStatus;
import com.stargate.ach.entity.TransferRequest;
import com.stargate.ach.exception.DBUpdateFailException;
import com.stargate.ach.exception.MulitpleDBRowEffectedException;
import com.stargate.ach.logging.BaseLogger;
import com.stargate.ach.service.ACHService;
import com.stargate.ach.validator.BLTransactionValidator;

@RestController
@RequestMapping("/ach")
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

	@Autowired
	private BLTransactionValidator blTransactionValidator;

	/***
	 * Builds the Uri for accessing the `executeTransfer` towards US-2 endpoints
	 ***/
	private String UriBuilder(String accountNo) {
		String url = address + "/transferfunds/" + accountNo + "/executeTransfer";
		return url;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(blTransactionValidator);
	}

	/***
	 * This method will receive input from the Mule Flow after the scatter-gather
	 * segregates the list of transactions. Each transaction will be sent to this
	 * endpoint through RESTful webservice from Mule
	 ****/
	@PostMapping("/persist")
	public ResponseEntity<ResponseStatus> persistTransfer(@RequestBody @Validated BLTransaction txn) {

		PersistResponseStatus respStatus = new PersistResponseStatus("SUCCESS : Data Persisted", "");

		if (txn == null) {
			respStatus.setStatus("FAIL");
			respStatus.setError("Null payload received");
			persistLogger.appendMessages("The BLTransaction object received is null");
			return new ResponseEntity<ResponseStatus>(respStatus, HttpStatus.ACCEPTED);
		}

		// save it in the transaction table
		service.addTransaction(txn);

		int sts = -1;
		try {
			sts = service.updateStatus(txn, txn.getStatus());
		} catch (DBUpdateFailException e) {
			respStatus.setStatus("FAIL");
			respStatus.setError(respStatus.getError() + "\n"
					+ "some problems with transactionId. It is possible that the autogeneration of Id failed");
			persistLogger.appendMessages("The data failed to persist due to incorrect transactionId");
		} catch (MulitpleDBRowEffectedException e) {
			respStatus.setStatus("FAIL");
			respStatus.setError(MulitpleDBRowEffectedException.getMesg());
			persistLogger
					.appendMessages("While persisting data multiple rows were effected. This is not a desired result");
		} finally {

			persistLogger.appendMessages(((sts == 1) ? "Updated" : "Failed to update") + " the transaction status! ");

			respStatus.setPersistId(txn.getTransactionId());
		}

		persistLogger.writeLogs();
		return new ResponseEntity<ResponseStatus>(respStatus, HttpStatus.ACCEPTED);

		// ResponseEntity<ResponseStatus> newResponseEntity = new
		// ResponseEntity<ResponseStatus>(HttpStatus.ACCEPTED); // JUST THE TESTING
		// ResponseStatus responseStatus = new ResponseStatus("SUCCESS",""); // JUST THE
		// TESTING
	}

	@PostMapping("/deliver-to-sender")
	public ResponseEntity<ResponseStatus> deliverRequestToSenderTransfer(@RequestBody @Validated BLTransaction txn) {

		// Default status is `InProgress`, change it into `HOLD` until the debit has
		// been successful. If it fails nothing happens
		int sts = -1;
		try {
			sts = service.updateStatus(txn, "HOLD");
		} catch (DBUpdateFailException e) {
			persistLogger.appendMessages("The data failed to persist due to incorrect transactionId");
			return new ResponseEntity<ResponseStatus>(new ResponseStatus("FAIL", "transactionId is Incorrect"),
					HttpStatus.ACCEPTED);
		} catch (MulitpleDBRowEffectedException e) {
			persistLogger
					.appendMessages("While persisting data multiple rows were effected. This is not a desired result");
			return new ResponseEntity<ResponseStatus>(
					new ResponseStatus("FAIL", "MulitpleDBRowEffectedException.getMesg()"), HttpStatus.ACCEPTED);
		} finally {
			persistLogger.appendMessages(
					((sts == 1) ? "Updated" : "Failed to update") + " the transaction status to HOLD! ");
		}

		// create a new TransferRequest POJO from the Transaction entity for the sender.
		// TODO we have assumed here that the sender is always DEBIT
		debitTransferRequest.buildDebitTransferRequest(txn);

		persistLogger.appendMessages("Received BLTransaction object is: " + txn.toString());

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

		// change the txn status where the response received is FAIL
		String txnSaveStatus = txn.getStatus();
		if (responseStatus.getStatus().equals("FAIL")) {
			txnSaveStatus = "FAIL to " + debitTransferRequest.getTransactionType() + " sender";
		}

		sts = -1;
		try {
			sts = service.updateStatus(txn, txnSaveStatus);
		} catch (DBUpdateFailException e) {
			persistLogger.appendMessages("The data failed to persist due to incorrect transactionId");
			responseStatus.setStatus("FAIL");
			responseStatus.setError(responseStatus.getError() + "transactionId is Incorrect");
		} catch (MulitpleDBRowEffectedException e) {
			persistLogger
					.appendMessages("While persisting data multiple rows were effected. This is not a desired result");
			responseStatus.setStatus("FAIL");
			responseStatus.setError(responseStatus.getError() + "MulitpleDBRowEffectedException.getMesg()");
		} finally {
			persistLogger.appendMessages(
					((sts == 1) ? "Updated" : "Failed to update") + " the transaction status to `InProgress`! ");
		}

		persistLogger.writeLogs();
		return new ResponseEntity<ResponseStatus>(responseStatus, HttpStatus.ACCEPTED);
	}

	@PostMapping("/deliver-to-receiver")
	public ResponseEntity<ResponseStatus> deliverRequestToReceiverTransfer(@RequestBody @Validated BLTransaction txn) {

		// Default status is `InProgress`, change it into `HOLD` until the credit has
		// been successful. If it fails nothing happens
		int sts = -1;
		try {
			sts = service.updateStatus(txn, "HOLD");
		} catch (DBUpdateFailException e) {
			persistLogger.appendMessages("The data failed to persist due to incorrect transactionId");
			return new ResponseEntity<ResponseStatus>(new ResponseStatus("FAIL", "transactionId is Incorrect"),
					HttpStatus.ACCEPTED);
		} catch (MulitpleDBRowEffectedException e) {
			persistLogger
					.appendMessages("While persisting data multiple rows were effected. This is not a desired result");
			return new ResponseEntity<ResponseStatus>(
					new ResponseStatus("FAIL", "MulitpleDBRowEffectedException.getMesg()"), HttpStatus.ACCEPTED);
		} finally {
			persistLogger.appendMessages(
					((sts == 1) ? "Updated" : "Failed to update") + " the transaction status to HOLD! ");
		}

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

		String txnSaveStatus = txn.getStatus();
		if (responseStatus.getStatus().equals("FAIL")) {
			txnSaveStatus = "FAIL to " + creditTransferRequest.getTransactionType() + " receiver";
		}

		sts = -1;
		try {
			sts = service.updateStatus(txn, txnSaveStatus);
		} catch (DBUpdateFailException e) {
			persistLogger.appendMessages("The data failed to persist due to incorrect transactionId");
			responseStatus.setStatus("FAIL");
			responseStatus.setError(responseStatus.getError() + "\n transactionId is Incorrect");
		} catch (MulitpleDBRowEffectedException e) {
			persistLogger
					.appendMessages("While persisting data multiple rows were effected. This is not a desired result");
			responseStatus.setStatus("FAIL");
			responseStatus.setError(responseStatus.getError() + "\n MulitpleDBRowEffectedException.getMesg()");
		} finally {
			persistLogger.appendMessages(
					((sts == 1) ? "Updated" : "Failed to update") + " the transaction status to `InProgress`! ");
		}

		persistLogger.writeLogs();
		return new ResponseEntity<ResponseStatus>(responseStatus, HttpStatus.ACCEPTED);
	}
}
