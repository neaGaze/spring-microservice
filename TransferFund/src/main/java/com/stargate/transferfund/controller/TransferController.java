package com.stargate.transferfund.controller;

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
import com.stargate.transferfund.entity.ResponseStatus;
import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.entity.TransferRequest;
import com.stargate.transferfund.exception.FailedDBUpdateException;
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
	@RequestMapping(value="/{bankId}/initiateTransfer", method=RequestMethod.POST)
	public ResponseEntity<ResponseStatus> getTransferDetails(@PathVariable("bankId") String bankId,
			@RequestBody Transaction transaction) {
		transferService.transfertoJMS(transaction);
		System.out.println("Transaction amount: "+transaction.getAmount());
		ResponseStatus status = new ResponseStatus("SUCCESS", "");
	    return new ResponseEntity<ResponseStatus>(status, HttpStatus.ACCEPTED);
    }
	
	
	/**
	 * This endpoint will receive payload from the ACH through Mule
	 * and perform the debit/credit on the account, and finally send
	 * the confirmation status back to the ACH through Mule Flow
	 ***/
	@RequestMapping(value="{bankName}/executeTransfer", method=RequestMethod.POST)
	public ResponseEntity<ResponseStatus> debitOrCreditAmount(@PathVariable("bankName") String bankName,
			@RequestBody TransferRequest transferRequest) {
		ResponseStatus status = new ResponseStatus();
		String returnStr = "";
		try {
			transferService.updateUniTransfer(transferRequest);
		} catch (FailedDBUpdateException e) {
			returnStr = e.getMessage();
			status.setError(e.getMessage());
			status.setStatus("FAILED");
		}
		status = new ResponseStatus("SUCCESS", returnStr);
		return new ResponseEntity<ResponseStatus>(status, HttpStatus.ACCEPTED);	
	}
}
