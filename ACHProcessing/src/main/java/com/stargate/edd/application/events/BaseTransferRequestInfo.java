package com.stargate.edd.application.events;

import java.io.Serializable;

public class BaseTransferRequestInfo implements Serializable {

	private static final long serialVersionUID = 1496446948688656714L;
	
	protected String from;
	protected String destination;
	protected Double amount;

	public BaseTransferRequestInfo() {
	}
	
	public BaseTransferRequestInfo(BaseTransferRequestInfo info) {
		this.from = info.from;
		this.destination = info.destination;
		this.amount = info.amount;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
