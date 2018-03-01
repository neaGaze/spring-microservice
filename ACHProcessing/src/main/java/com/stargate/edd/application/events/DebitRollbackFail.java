package com.stargate.edd.application.events;

import java.util.UUID;

public class DebitRollbackFail extends BaseTransferEvent {

	private static final long serialVersionUID = -1675329190128762925L;
	
	private UUID id;

	public DebitRollbackFail() {}

	public DebitRollbackFail(UUID id) {
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
