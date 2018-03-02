package com.stargate.edd.application.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stargate.edd.application.entity.ACHPlacedSuccess;
import com.stargate.edd.application.entity.ACHResponse;
import com.stargate.edd.application.entity.TransferRequestState;
import com.stargate.edd.application.events.BaseTransferRequestInfo;
import com.stargate.edd.application.events.TransferRequestInfo;
import com.stargate.edd.application.service.QueryService;
import com.stargate.edd.application.service.TransferCommandService;
import com.stargate.edd.application.validator.TransactionValidator;

@Controller
public class TransferController {

	@Autowired
	private TransferCommandService commandService;
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private TransactionValidator transactionValidator;

	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(transactionValidator);
	}

	@PostMapping(value="/post-transfer")
	public ResponseEntity<ACHResponse> postTransferRequest(@RequestBody @Validated BaseTransferRequestInfo info) {
		TransferRequestInfo infoWithId = new TransferRequestInfo(info);
		commandService.placeTransferRequest(infoWithId);
		return new ResponseEntity<ACHResponse>(new ACHPlacedSuccess(infoWithId.getId().toString()), HttpStatus.ACCEPTED);
	}
	

	@GetMapping(path = "/check-status")
	public @ResponseBody ResponseEntity<TransferRequestState> getTransactionStatus(@RequestParam String transaction_id) {
		TransferRequestState state = queryService.getInfo(UUID.fromString(transaction_id));
		return new ResponseEntity<TransferRequestState>(state, HttpStatus.ACCEPTED);
	}
}
