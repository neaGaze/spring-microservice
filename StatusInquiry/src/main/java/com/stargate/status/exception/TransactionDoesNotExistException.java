package com.stargate.status.exception;

public class TransactionDoesNotExistException extends RuntimeException{
	private static final long serialVersionUID = 405802649322364762L;

	public TransactionDoesNotExistException(String message) {
		super(message);
	}

	public TransactionDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
