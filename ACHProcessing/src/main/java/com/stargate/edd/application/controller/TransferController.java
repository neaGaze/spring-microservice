package com.stargate.edd.application.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stargate.edd.application.entity.TransferRequestState;
import com.stargate.edd.application.events.TransferRequestInfo;
import com.stargate.edd.application.service.QueryService;
import com.stargate.edd.application.service.TransferCommandService;

@Controller
public class TransferController {

	@Autowired
	private TransferCommandService commandService;
	
	@Autowired
	private QueryService queryService;
	
	@PostMapping(value="/post-transfer")
	public ResponseEntity postTransferRequest(@RequestBody TransferRequestInfo info) {

        final UUID id = UUID.randomUUID();
        info.setId(id);
        commandService.placeTransferRequest(info);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	

	@GetMapping(path = "/check-status")
	public @ResponseBody ResponseEntity<TransferRequestState> getTransactionStatus(@RequestParam String transaction_id) {
		TransferRequestState state = queryService.getInfo(UUID.fromString(transaction_id));
		return new ResponseEntity<TransferRequestState>(state, HttpStatus.ACCEPTED);
	}
}
