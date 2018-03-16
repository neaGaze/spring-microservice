package com.stargate.edd2.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.stargate.edd.application.events.TransferRequestInfo;
import com.stargate.edd2.application.entity.ACHResponse;
import com.stargate.edd2.application.entity.AchErrorResponse;
import com.stargate.edd2.application.entity.AchSimpleResponse;
import com.stargate.edd2.application.service.TransferFundService;
import com.stargate.edd2.application.validation.TransactionValidator;

@Controller
public class BaseController {

	@Autowired
	public TransferFundService commandService;

	@Autowired
	private TransactionValidator transactionValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(transactionValidator);
	}
	
	@PostMapping(value="/debit")
	public ResponseEntity<ACHResponse> performDebit(@RequestBody @Validated TransferRequestInfo body) {
		System.out.println("DEBIT call received as " + body.toString());
		ACHResponse returnBody;
		if(body.getId() == null)
			returnBody = new AchErrorResponse("DEBIT_FAILED", "transactionId is not valid");
		else {
			commandService.placeDebitEvent(body);
			returnBody = new AchSimpleResponse("Debit command received");
		}
		return new ResponseEntity<ACHResponse>(returnBody, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/credit")
	public ResponseEntity<ACHResponse> performCredit(@RequestBody @Validated TransferRequestInfo body) {
		ACHResponse returnBody;
		if(body.getId() == null)
			returnBody = new AchErrorResponse("CREDIT_FAILED", "transactionId is not valid");
		else {
			commandService.placeCreditEvent(body);
			returnBody = new AchSimpleResponse("Credit command received");
		}
		return new ResponseEntity<ACHResponse>(returnBody, HttpStatus.ACCEPTED);
	}
}
