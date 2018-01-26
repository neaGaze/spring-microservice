package com.stargate.transferfund.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class InvalidRequestException extends Exception {

	private static String ERR_INVALID_METHOD = "You passed an invalid method or address";
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return ERR_INVALID_METHOD;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintStream s) {
		// TODO Auto-generated method stub
		super.printStackTrace(s);
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		// TODO Auto-generated method stub
		super.printStackTrace(s);
	}

	@Override
	public void setStackTrace(StackTraceElement[] stackTrace) {
		// TODO Auto-generated method stub
		super.setStackTrace(stackTrace);
	}

}
