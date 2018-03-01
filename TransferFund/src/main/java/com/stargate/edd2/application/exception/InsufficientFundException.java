package com.stargate.edd2.application.exception;

import org.springframework.stereotype.Component;

@Component
public class InsufficientFundException extends Exception {

	private static final long serialVersionUID = -1302423116257479120L;

	private static final String errMsg = "Failed to execute transfers!! Make sure that the balance is sufficient enough. Check if the account No is valid";
	
	/*
	@Autowired
	private BaseLogger failedDBUpdateExceptionLogger;*/
	
/*	
	//private BaseLogger logger;
	 
    public BaseLogger getLogger() {
		return logger;
	}

	public void setLogger(BaseLogger logger) {
		this.logger = logger;
		BaseLogger accountDoesNotExistLogger = new ConsoleLogger(FailedDBUpdateException.class, errMsg);
		this.logger.setSuccessor(accountDoesNotExistLogger);
	}
*/
	@Override
	public String getMessage() {
		return errMsg;
	}

}
