package com.stargate.ach.entity;

public class PersistResponseStatus extends ResponseStatus{

	private Integer persistId;
	
	public PersistResponseStatus(String status, String error) {
		super(status, error);
		persistId = -1;
	}
	
	public Integer getPersistId() {
		return persistId;
	}

	public void setPersistId(Integer persistId) {
		this.persistId = persistId;
	}
}
