package com.stargate.edd2.application.entity;

import java.io.Serializable;

public class AchSimpleResponse extends ACHResponse implements Serializable {

	private static final long serialVersionUID = -8523257503597809040L;
	
	private String message;

	public AchSimpleResponse() {}
	
	public AchSimpleResponse(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
