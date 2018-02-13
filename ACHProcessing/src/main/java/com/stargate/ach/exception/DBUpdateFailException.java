package com.stargate.ach.exception;

public class DBUpdateFailException extends Exception {
	
	private static final long serialVersionUID = -4125105307984934357L;
	
	@Override
	public String getMessage() {
		return "failed to update the status of transaction";
	}
}
