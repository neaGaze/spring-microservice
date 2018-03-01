package com.stargate.edd.application.events;

import java.util.UUID;

public class CreditRollbackFail extends BaseTransferEvent {

	private static final long serialVersionUID = -4986597613208806514L;

	private UUID id;

	public CreditRollbackFail() {}

	public CreditRollbackFail(UUID id) {
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
