package com.stargate.statusquery.edd.application.exception;

import org.springframework.stereotype.Component;

@Component
public class TransactionDoesNotExistException extends Exception {
	
	private static final String errMsg = "Failed to find the transaction. Check if the transactionId provided is correct";
	
	private static final long serialVersionUID = -3876049645792679121L;

	@Override
	public String getMessage() {
		return errMsg;
	}
}
