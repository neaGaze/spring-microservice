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

/*ACCEPTANCE CRITERIA
 * 
 * Bank searches for the transaction status after payment origination (PENDING).
 * Bank searches for the transaction status that was successfully processed
(PROCESSED).
 * Bank searches for the transaction status that was rejected (reason could be the
insufficient funds or the bank account number error) (ERROR)
*/

@Controller
@RequestMapping(path = "/getstatus")
public class GetStatusController {

	@Autowired
	private GetStatusRepository getStatusRepository;

	@GetMapping(path = "/check-status")
	public @ResponseBody String getTransactionStatus(@RequestParam String transaction_id) {
		Transaction txn = getStatusRepository.findOne(transaction_id);
		if (txn == null) {
			throw new TransactionDoesNotExistException("Transaction: " + transaction_id + " does not exist ");
		}
		return txn.getStatus();
	}

}
