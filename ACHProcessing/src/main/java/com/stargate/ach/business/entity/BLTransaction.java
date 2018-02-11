package com.stargate.ach.business.entity;

import java.io.Serializable;
import com.stargate.ach.entity.ACHTransaction;

/**
 * 
 */
public class BLTransaction implements Serializable {

	private static final long serialVersionUID = -4450261208250531230L;
	
	private Integer transactionId;

	private String senderDetails;
	
	private String receiverDetails;
	
	private Double amount;
	
	private String status;
	

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	
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

	@Override
	public String toString() {
		return "BLTransaction [transaction_id=" + transactionId + ", senderDetails=" + senderDetails
				+ ", receiverDetails=" + receiverDetails + ", amount=" + amount + ", status=" + status + "]";
	}
	
	public ACHTransaction convertBLIntoDBEntity() {
		ACHTransaction transaction = new ACHTransaction();
		transaction.setAmount(this.getAmount());
		transaction.setReceiverDetails(this.getReceiverDetails());
		transaction.setSenderDetails(this.getSenderDetails());
		transaction.setStatus(this.getStatus());
		return transaction;
	}
}
