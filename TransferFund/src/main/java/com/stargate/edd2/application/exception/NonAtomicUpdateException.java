package com.stargate.edd2.application.exception;

import org.springframework.stereotype.Component;

@Component
public class NonAtomicUpdateException extends Exception {
	
	private static final long serialVersionUID = -5157070758794537697L;
	private static final String errMsg = "Failed to execute transfers!! Multiple transactions updated simultaneously. Make sure you have unique transactions persisted";
	
	@Override
	public String getMessage() {
		return errMsg;
	}
}
