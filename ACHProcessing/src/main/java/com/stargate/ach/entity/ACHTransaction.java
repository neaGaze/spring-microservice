package com.stargate.ach.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="transaction")
public class ACHTransaction implements Serializable {

	private static final long serialVersionUID = 8902759812042977162L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer transaction_id;
	
	private String senderDetails;
	
	private String receiverDetails;
	
	private Double amount;
	
	private String status;

	public Integer getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(Integer transaction_id) {
		this.transaction_id = transaction_id;
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
