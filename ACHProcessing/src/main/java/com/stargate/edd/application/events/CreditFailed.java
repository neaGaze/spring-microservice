package com.stargate.edd.application.events;

import java.util.UUID;

public class CreditFailed extends BaseTransferEvent {

	private static final long serialVersionUID = 3114694167891688813L;
	private UUID id;
	private String reason;
	
	public CreditFailed() {}
	
	public CreditFailed(UUID id, String reason) {
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
