package com.stargate.transferfund.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

//@Entity
public class Transaction implements Serializable {

	//@Id
	//private Integer id;

	protected Account senderDetails;

	protected Account receiverDetails;

	private Double amount;
	
	private TransactionType transactionType;
/*	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	*/
	public Account getSenderDetails() {
		return senderDetails;
	}

	public void setSenderDetails(Account senderDetails) {
		this.senderDetails = senderDetails;
	}

	public Account getReceiverDetails() {
		return receiverDetails;
	}

	public void setReceiverDetails(Account receiverDetails) {
		this.receiverDetails = receiverDetails;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
}
