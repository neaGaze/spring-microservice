package com.stargate.edd.application.events;

import java.util.UUID;

public class TransferCompleted extends BaseTransferEvent {
	
	private static final long serialVersionUID = -4893002778564001930L;
	
	private UUID id;

	public TransferCompleted() {}
	
	public TransferCompleted(UUID id) {
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
}
