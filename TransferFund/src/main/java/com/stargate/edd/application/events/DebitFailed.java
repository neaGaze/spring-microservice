package com.stargate.edd.application.events;

import java.util.UUID;

public class DebitFailed extends BaseTransferEvent {

	private static final long serialVersionUID = -1707986856557586347L;

	private UUID id;
	private String reason;
	
	public DebitFailed() {}
	
	public DebitFailed(UUID id, String reason) {
		this.setId(id);
		this.reason = reason;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
