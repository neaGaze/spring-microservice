package com.stargate.edd.application.events;

import java.io.Serializable;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TransferRequestInfo extends BaseTransferRequestInfo implements Serializable {
	
	private static final long serialVersionUID = -2131199180712701960L;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	private UUID id;
	
	public TransferRequestInfo() {}

	public TransferRequestInfo(BaseTransferRequestInfo info) {
		super(info);
		this.id = UUID.randomUUID();
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TransferRequestInfo [id=" + id + ", from=" + from + ", destination=" + destination + ", amount="
				+ amount + "]";
	}
}
