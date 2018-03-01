package com.stargate.edd.application.events;

import java.util.UUID;

public class TransferRejected extends BaseTransferEvent {
	
	private static final long serialVersionUID = 4158604559975817459L;
	
	private UUID id;
	private String reason;

	protected TransferRejected(Object obj) {
		super(obj);
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
