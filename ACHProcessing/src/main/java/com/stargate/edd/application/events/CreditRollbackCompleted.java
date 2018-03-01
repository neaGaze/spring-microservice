package com.stargate.edd.application.events;

import java.util.UUID;

public class CreditRollbackCompleted extends BaseTransferEvent {

	private static final long serialVersionUID = -4414224705530896745L;

	private UUID id;

	public CreditRollbackCompleted() {}

	public CreditRollbackCompleted(UUID id) {
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
