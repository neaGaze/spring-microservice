package com.stargate.edd.application.events;

import java.util.UUID;

public class CreditCompleted extends BaseTransferEvent {

	private static final long serialVersionUID = -3557869145199689236L;

	private UUID id;

	public CreditCompleted() {}

	public CreditCompleted(UUID id) {
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}