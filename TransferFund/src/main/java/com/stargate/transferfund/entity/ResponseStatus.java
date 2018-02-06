package com.stargate.transferfund.entity;

public class ResponseStatus {

	private String status;
	
	private String error;
	
	public ResponseStatus() {}
	
	public ResponseStatus(String status, String error) {
		this.status = status;
		this.error = error;
	}
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
