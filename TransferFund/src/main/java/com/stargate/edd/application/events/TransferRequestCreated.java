package com.stargate.edd.application.events;

import java.io.Serializable;

public class TransferRequestCreated extends BaseTransferEvent implements Serializable {

	private static final long serialVersionUID = -6223458029226933500L;
	
	private TransferRequestInfo info;
	
	public TransferRequestCreated() {
	}
	
	public TransferRequestCreated(Object obj, TransferRequestInfo info) {
		super(obj);
		this.info = info;
	}
	
	public TransferRequestInfo getInfo() {
		return info;
	}

	public void setInfo(TransferRequestInfo info) {
		this.info = info;
	}
	
	@Override
	public String toString() {
		return "TransferRequestCreated [info=" + info.toString() + ", getInstant()=" + getInstant() + "]";
	}
}
