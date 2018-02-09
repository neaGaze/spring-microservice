package com.stargate.transferfund.business.entity;

import java.io.Serializable;

public class BLTransaction implements Serializable {

	public String getSenderDetails() {
		return senderDetails;
	}

	public void setSenderDetails(String senderDetails) {
		this.senderDetails = senderDetails;
	}

	public String getReceiverDetails() {
		return receiverDetails;
	}

	public void setReceiverDetails(String receiverDetails) {
		this.receiverDetails = receiverDetails;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4450261208250531230L;

	private String senderDetails;
	
	private String receiverDetails;
	
	private Double amount;
	
	private String status;
}
