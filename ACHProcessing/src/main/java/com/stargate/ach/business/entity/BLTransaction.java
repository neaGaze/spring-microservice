package com.stargate.ach.business.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name="transaction")
public class BLTransaction implements Serializable {

	private static final long serialVersionUID = -4450261208250531230L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer transaction_id;

	private String senderDetails;
	
	private String receiverDetails;
	
	private Double amount;
	
	private String status;
	

	public Integer getTransactionId() {
		return transaction_id;
	}

	public void setTransactionId(Integer transactionId) {
		this.transaction_id = transactionId;
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
}
