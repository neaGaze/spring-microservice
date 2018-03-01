package com.stargate.ach.entity;

public class ResponseStatus {
	private String status;
	private String message;
	
	public ResponseStatus() {}
	
	public ResponseStatus(String status, String error){
		this.status = status;
		this.message = error;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String error) {
		this.message = error;
	}
	
	
}
