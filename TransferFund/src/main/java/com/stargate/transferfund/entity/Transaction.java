package com.stargate.transferfund.entity;

import java.io.Serializable;

import javax.persistence.*;

//@Entity
public class Transaction implements Serializable {

	//@Id
	//private Integer id;
	
	private Account senderDetails;

	private Account receiverDetails;
	
	private Double amount;
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
}
