package com.stargate.edd.application.events;

import java.util.UUID;

public class TransferCompleted extends BaseTransferEvent {
	
	private static final long serialVersionUID = -4893002778564001930L;
	
	private UUID id;

	protected TransferCompleted(Object obj) {
		super(obj);
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
}
