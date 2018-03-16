package com.stargate.edd2.application.entity;

import java.io.Serializable;

public class AchErrorResponse extends ACHResponse implements Serializable {

	private static final long serialVersionUID = -4221191963393063732L;

	private String error, description;

	public AchErrorResponse(String error, String desc) {
		this.error = error;
		this.description = desc;
	}
	
	public AchErrorResponse() {}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
