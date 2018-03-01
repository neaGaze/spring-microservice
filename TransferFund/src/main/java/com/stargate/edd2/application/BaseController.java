package com.stargate.edd2.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.stargate.edd.application.events.TransferRequestInfo;
import com.stargate.edd2.application.service.TransferFundService;

@Controller
public class BaseController {

	@Autowired
	public TransferFundService commandService;
	
	@PostMapping(value="/debit")
	public ResponseEntity performDebit(@RequestBody TransferRequestInfo body) {
		System.out.println("DEBIT call received as " + body.toString());
		commandService.placeDebitEvent(body);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/credit")
	public ResponseEntity performCredit(@RequestBody TransferRequestInfo body) {
		commandService.placeCreditEvent(body);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
}
