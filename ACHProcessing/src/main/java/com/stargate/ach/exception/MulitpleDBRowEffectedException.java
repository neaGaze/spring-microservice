package com.stargate.ach.exception;

/***
 * This exception is thrown when a multiple rows in a db is updated
 * ***/
public class MulitpleDBRowEffectedException extends Exception {


	private static final String mesg = "Mulitple rows in a database is effected";
	
	@Override
	public String getMessage() {
		return mesg;
	}

	public static String getMesg() {
		return mesg;
	}
	
}
