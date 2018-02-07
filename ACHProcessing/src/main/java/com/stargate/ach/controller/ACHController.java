package com.stargate.ach.controller;

import java.net.URI;

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
import com.stargate.ach.entity.ResponseStatus;
import com.stargate.ach.entity.Transaction;
import com.stargate.ach.entity.TransferRequest;
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
	
	// @Autowired
	// private ACHRepository repository;
	
	/***
	 * This method will receive input from the Mule Flow after the scatter-gather
	 * segregates the list of transactions. Each transaction will be sent to this
	 * endpoint through RESTful webservice from Mule
	 ****/
	@PostMapping("/transaction")
	public Integer/* ResponseStatus */ initiateTransfer(@RequestBody Transaction txn) {

		if (txn == null)
			return null;

		// save it in the transaction table
		service.addTransaction(txn);

		// create a new TransferRequest POJO from the Transaction entity for the sender
		TransferRequest transferRequest = TransferRequest.buildDebitTransferRequest(txn);

		ResponseEntity<TransferRequest> entity = new ResponseEntity<TransferRequest>(transferRequest,
				HttpStatus.ACCEPTED);
		
		// URL to send the request to Bank A
		String url = address + "/transferfunds/"+transferRequest.getAccount().getBankName()+"/executeTransfer";
		System.out.println("Sending request to : " + url);
		System.out.println("TransferRequest obj -> " + transferRequest.toString());
		
		ResponseEntity<ResponseStatus> newResponseEntity = restTemplate.exchange(
				url, HttpMethod.POST, entity,
				ResponseStatus.class);

		ResponseStatus responseStatus = (ResponseStatus)newResponseEntity.getBody();
		System.out.println("Response after the bank A sends us the response "+responseStatus.getStatus());
		
		return service.updateStatus(txn, newResponseEntity.getStatusCode().toString());

		// ResponseEntity<ResponseStatus> newResponseEntity = new
		// ResponseEntity<ResponseStatus>(HttpStatus.ACCEPTED); // JUST THE TESTING
		// ResponseStatus responseStatus = new ResponseStatus("SUCCESS",""); // JUST THE
		// TESTING

		/*
		 * URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
		 * "/{id}").buildAndExpand(txn.getId()).toUri();
		 * 
		 * return ResponseEntity.created(location).build();
		 */
		// return newResponseEntity;
	}

	/*
	 * @RequestMapping("/ach/execute/{transactionID}") public ResponseEntity
	 * executeTransfer(Integer transactionID) {
	 * 
	 * }
	 * 
	 * @PostMapping("/ach/{status}") public ResponseStatus
	 * updateStatus(@PathVariable String status) {
	 * 
	 * return service.updateStatus(status); }
	 */
}
