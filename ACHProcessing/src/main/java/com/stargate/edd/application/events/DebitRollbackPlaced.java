package com.stargate.edd.application.events;

public class DebitRollbackPlaced extends BaseTransferEvent {

	private static final long serialVersionUID = 5344673141652318643L;
	
	private TransferRequestInfo info;

	public DebitRollbackPlaced() {}

	public DebitRollbackPlaced(TransferRequestInfo info) {
		this.info = info;
	}
	
	public TransferRequestInfo getInfo() {
		return info;
	}

	public void setInfo(TransferRequestInfo info) {
		this.info = info;
	}
}
