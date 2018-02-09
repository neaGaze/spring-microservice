package com.stargate.ach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.stargate.ach.business.entity.BLTransaction;
import com.stargate.ach.entity.ResponseStatus;
import com.stargate.ach.entity.TransferRequest;
import com.stargate.ach.exception.MulitpleDBRowEffectedException;
import com.stargate.ach.service.ACHService;

@RestController
@RequestMapping("ach")
public class ACHController {
	
	@Autowired
	private ACHService service;

	@Autowired
	RestTemplate restTemplate;

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

		ResponseStatus respStatus = new ResponseStatus("SUCCESS : Data Persisted", "");
		
		if (txn == null) {
			respStatus.setStatus("FAIL");
			respStatus.setError("Null payload received");
			return new ResponseEntity<ResponseStatus> (respStatus, HttpStatus.ACCEPTED);
		}
		
		// save it in the transaction table
		service.addTransaction(txn);

		if(service.updateStatus(txn, respStatus.getError()) > 1) {
			respStatus.setStatus("FAIL"); 
			respStatus.setError(MulitpleDBRowEffectedException.getMesg());
		} else {
			respStatus.setStatus("FAIL"); 
			respStatus.setError("Data Persist Failed");
		}
		
		return new ResponseEntity<ResponseStatus> (respStatus, HttpStatus.ACCEPTED);

		// ResponseEntity<ResponseStatus> newResponseEntity = new
		// ResponseEntity<ResponseStatus>(HttpStatus.ACCEPTED); // JUST THE TESTING
		// ResponseStatus responseStatus = new ResponseStatus("SUCCESS",""); // JUST THE
		// TESTING
	}

	@PostMapping("/deliver-to-sender")
	public ResponseEntity<ResponseStatus> deliverRequestToSenderTransfer(@RequestBody BLTransaction txn) {

		// create a new TransferRequest POJO from the Transaction entity for the sender.
		// TODO we have assumed here that the sender is always DEBIT
		TransferRequest transferRequest = TransferRequest.buildDebitTransferRequest(txn);

		// URL to send the request to SENDER Bank 
		String url = UriBuilder(transferRequest.getAccountNo());
		ResponseEntity<TransferRequest> entity = new ResponseEntity<TransferRequest>(transferRequest,
				HttpStatus.ACCEPTED);
		
		ResponseEntity<ResponseStatus> newResponseEntity = restTemplate.exchange(
				url, HttpMethod.POST, entity,
				ResponseStatus.class);

		ResponseStatus responseStatus = (ResponseStatus) newResponseEntity.getBody();
		return new ResponseEntity<ResponseStatus>(responseStatus, HttpStatus.ACCEPTED);	
	}
	

	@PostMapping("/deliver-to-receiver")
	public ResponseEntity<ResponseStatus> deliverRequestToReceiverTransfer(@RequestBody BLTransaction txn) {

		// create a new TransferRequest POJO from the Transaction entity for the receiver.
		// TODO we have assumed here that the receiver is always CREDIT
		TransferRequest transferRequest = TransferRequest.buildCreditTransferRequest(txn);

		// URL to send the request to RECEIVER Bank 
		String url = UriBuilder(transferRequest.getAccountNo());
		
		ResponseEntity<TransferRequest> entity = new ResponseEntity<TransferRequest>(transferRequest,
				HttpStatus.ACCEPTED);
		
		ResponseEntity<ResponseStatus> newResponseEntity = restTemplate.exchange(
				url, HttpMethod.POST, entity,
				ResponseStatus.class);

		ResponseStatus responseStatus = (ResponseStatus) newResponseEntity.getBody();
		return new ResponseEntity<ResponseStatus>(responseStatus, HttpStatus.ACCEPTED);	
	}
}
