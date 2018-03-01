package com.stargate.edd.application.events;

public class DebitTxnPlaced extends BaseTransferEvent {

	private static final long serialVersionUID = -6282803062728065920L;

	private TransferRequestInfo info;

	public DebitTxnPlaced() {}

	public DebitTxnPlaced(TransferRequestInfo info) {
		this.info = info;
	}
	
	public TransferRequestInfo getInfo() {
		return info;
	}

	public void setInfo(TransferRequestInfo info) {
		this.info = info;
	}
}
