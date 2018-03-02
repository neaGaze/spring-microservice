package com.stargate.edd.application.events;

import java.util.UUID;

public class TransferRejected extends BaseTransferEvent {
	
	private static final long serialVersionUID = 4158604559975817459L;
	
	private UUID id;
	private String reason;

	public TransferRejected() {}
	
	public TransferRejected(UUID id, String reason) {
		this.id = id;
		this.reason = reason;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
