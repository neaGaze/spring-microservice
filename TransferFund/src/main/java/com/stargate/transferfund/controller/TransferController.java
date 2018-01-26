package com.stargate.transferfund.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.stargate.transferfund.entity.Bank;
import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.service.TransferService;

@Controller
@RequestMapping("transferfunds")
public class TransferController {
	
	@Autowired
	@Qualifier(value="transferServiceImpl")
	private TransferService transferService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
		// like an initializer
	}
	
	/**
	 * This endpoint will receive payload from the Mule regarding
	 * the details of transactions including the sender info, receiver
	 * info and amount to transfer 
	 ***/
	@RequestMapping(value="/{bankId}/intiateTransfer", method=RequestMethod.POST)
	public ResponseEntity getTransferDetails(@PathVariable("bankId") String bankId,
			@RequestBody Transaction transaction) {

		//List<Bank> lists = transferService.findAll();
		System.out.println("Transaction amount: "+transaction.getAmount());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
	
	
	/**
	 * This endpoint will receive payload from the ACH through Mule
	 * and perform the debit/credit on the account, and finally send
	 * the confirmation status back to the ACH through Mule Flow
	 ***/
	@RequestMapping(value="/send", method=RequestMethod.GET)
	public ResponseEntity debitOrCreditAmount() {
		
        return new ResponseEntity(new Integer(4), HttpStatus.ACCEPTED);
    }
}
