package com.stargate.edd.application.entity;

import java.util.UUID;

import com.stargate.edd.application.events.TransferRequestInfo;
import com.stargate.edd.application.peristence.mock.States;

/**
 * This event is stored in the `EventStore` NoSQL db 
 ***/
public class TransferRequestState {
	
	private TransferRequestInfo info;
	private States state;
	
	public TransferRequestState() {}

	public TransferRequestState(String status) {
		this.state = States.valueOf(status);
	}
	
	public static TransferRequestState build(ACHTransaction ach) {
		TransferRequestState state = new TransferRequestState(ach.getStatus());
		TransferRequestInfo info = new TransferRequestInfo();
		info.setId(UUID.fromString(ach.getId()));
		info.setFrom(ach.getFrom());
		info.setDestination(ach.getTo());
		info.setAmount(ach.getAmount());
		state.setInfo(info);
		
		return state;
	}
	
	/*public TransferRequestState(TransferRequestInfo info) {
		this.info = info;
	}
	
	public TransferRequestState(TransferRequestInfo info, States state) {
		this.info = info;
		this.state = state;
	}*/
	
	public TransferRequestInfo getInfo() {
		return info;
	}
	public void setInfo(TransferRequestInfo info) {
		this.info = info;
	}
	
	public States getState() {
		return state;
	}
	
	public void place(TransferRequestInfo info) {
		this.info = info;
		this.state = States.PLACED;
	}

	public void accept() {
		this.state = States.ACCEPTED;
	}
	
	public void reject() {
		this.state = States.REJECTED;
	}

	public void debitPlaced() {
		this.state = States.DEBIT_PLACED;
	}
	
	public void debitComplete() {
		this.state = States.DEBIT_COMPLETE;
	}
	
	public void debitFail() {
		this.state = States.DEBIT_FAIL;
	}
	
	public void creditPlaced() {
		this.state = States.CREDIT_PLACED;
	}

	public void creditComplete() {
		this.state = States.CREDIT_COMPLETE;
	}
	
	public void creditFail() {
		this.state = States.CREDIT_FAIL;
	}
	
	public void complete() {
		this.state = States.COMPLETED;
	}

	@Override
	public String toString() {
		return "TransferRequestState [info=" + info.toString() + ", state=" + state + "]";
	}
}
