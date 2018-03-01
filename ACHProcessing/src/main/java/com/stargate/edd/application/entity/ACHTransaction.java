package com.stargate.edd.application.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.stargate.edd.application.events.TransferRequestInfo;

@Entity
@Table(name="transaction")
public class ACHTransaction implements Serializable {

	private static final long serialVersionUID = -8222182494527265792L;

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	private String sender;

	private String receiver;
	
	private Double amount;
	
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String transaction_id) {
		this.id = transaction_id;
	}

	public String getFrom() {
		return sender;
	}

	public void setFrom(String senderDetails) {
		this.sender = senderDetails;
	}

	public String getTo() {
		return receiver;
	}

	public void setTo(String receiverDetails) {
		this.receiver = receiverDetails;
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

	
	public static ACHTransaction convertFromInfo(TransferRequestState state) {
		ACHTransaction ach = new ACHTransaction();
		ach.setId(state.getInfo().getId().toString());
		ach.setFrom(state.getInfo().getFrom());
		ach.setTo(state.getInfo().getDestination());
		ach.setAmount(state.getInfo().getAmount());
		ach.setStatus(state.getState().toString());
		return ach;
	}
}
