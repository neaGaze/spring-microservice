package com.stargate.edd.application.events;

public class CreditRollbackPlaced extends BaseTransferEvent {

	private static final long serialVersionUID = 2739905728055949785L;
	
	private TransferRequestInfo info;

	public CreditRollbackPlaced() {}

	public CreditRollbackPlaced(TransferRequestInfo info) {
		this.info = info;
	}
	
	public TransferRequestInfo getInfo() {
		return info;
	}

	public void setInfo(TransferRequestInfo info) {
		this.info = info;
	}
}
