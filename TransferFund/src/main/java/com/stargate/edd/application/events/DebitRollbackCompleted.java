package com.stargate.edd.application.events;

import java.util.UUID;

public class DebitRollbackCompleted extends BaseTransferEvent {

	private static final long serialVersionUID = -202855407725981687L;

	private UUID id;

	public DebitRollbackCompleted() {}

	public DebitRollbackCompleted(UUID id) {
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
