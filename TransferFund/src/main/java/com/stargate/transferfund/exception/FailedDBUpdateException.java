package com.stargate.transferfund.exception;

public class FailedDBUpdateException extends Exception {

	@Override
	public String getMessage() {
		return "Failed to execute transfers!!";
	}

}
