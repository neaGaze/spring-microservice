package com.stargate.status.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stargate.status.entity.Transaction;
import com.stargate.status.exception.TransactionDoesNotExistException;
import com.stargate.status.repository.GetStatusRepository;


@Controller
@RequestMapping(path = "/transaction")
public class GetStatusController {

	@Autowired
	private GetStatusRepository getStatusRepository;

	
	@GetMapping(path="/find")
	public @ResponseBody String getTransactionStatus (@RequestParam String transaction_id) {
		Transaction txn = getStatusRepository.findOne(transaction_id);
		if(txn==null) {
			throw new TransactionDoesNotExistException("Transaction: " +transaction_id+" does not exist ");
		}
		return txn.getStatus();
	}

	
}

