package com.stargate.edd.application.events;

public class CreditTxnPlaced extends BaseTransferEvent{

	private static final long serialVersionUID = -890945453531395434L;

	private TransferRequestInfo info;

	public CreditTxnPlaced() {}

	public CreditTxnPlaced(TransferRequestInfo info) {
		this.info = info;
	}
	
	public TransferRequestInfo getInfo() {
		return info;
	}

	public void setInfo(TransferRequestInfo info) {
		this.info = info;
	}
}
