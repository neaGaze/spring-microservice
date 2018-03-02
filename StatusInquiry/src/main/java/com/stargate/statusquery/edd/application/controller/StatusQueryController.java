package com.stargate.statusquery.edd.application.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stargate.statusquery.edd.application.entity.TransferRequestState;
import com.stargate.statusquery.edd.application.service.QueryService;

@Controller
@RequestMapping(path = "/getstatus")
public class StatusQueryController {
	
	@Autowired
	private QueryService queryService;
	
	@GetMapping(path = "/check-status")
	public @ResponseBody ResponseEntity<TransferRequestState> getTransactionStatus(@RequestParam String transaction_id) {
		TransferRequestState state = queryService.getTransactionState(UUID.fromString(transaction_id));
		return new ResponseEntity<TransferRequestState>(state, HttpStatus.ACCEPTED);
	}
	
}
