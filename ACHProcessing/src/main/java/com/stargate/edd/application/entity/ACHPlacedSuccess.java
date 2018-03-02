package com.stargate.edd.application.entity;

public class ACHPlacedSuccess extends ACHResponse {

	private String transactionId;

	public ACHPlacedSuccess(String id) {
		this.transactionId = id;
	}
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
