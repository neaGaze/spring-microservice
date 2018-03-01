package com.stargate.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.stargate.logging.BaseLogger;
import com.stargate.logging.ConsoleLogger;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AccountDoesNotExistException extends Exception implements Serializable {
	
	private static final long serialVersionUID = -95480147332808654L;

	private static final String errMsg = "The requested Account doesn't exist in the record.";
	
	private BaseLogger logger;

	public AccountDoesNotExistException(String msg) {
		super(msg);
	}

	public AccountDoesNotExistException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public BaseLogger getLogger() {
		return logger;
	}

	public void setLogger(BaseLogger logger) {
		this.logger = logger;
		//BaseLogger accountDoesNotExistLogger = new ConsoleLogger(AccountDoesNotExistException.class.getName(), errMsg);
		//this.logger.setSuccessor(accountDoesNotExistLogger);
	}

	@Override
	public String getMessage() {
		return errMsg;
	}
}
