package com.stargate.edd.application.events;

import java.util.UUID;

public class RequestValidatedEvent extends BaseTransferEvent {

	private static final long serialVersionUID = 7622673752442107602L;

	private UUID id;

	public RequestValidatedEvent() {}
	
	public RequestValidatedEvent(UUID id) {
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "RequestValidatedEvent [id=" + id.toString() + "]";
	}
}
