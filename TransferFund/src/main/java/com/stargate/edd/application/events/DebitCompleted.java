package com.stargate.edd.application.events;

import java.util.UUID;

public class DebitCompleted extends BaseTransferEvent {

	private static final long serialVersionUID = 9109345909960163383L;

	private UUID id;

	public DebitCompleted() {}

	public DebitCompleted(UUID id) {
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
