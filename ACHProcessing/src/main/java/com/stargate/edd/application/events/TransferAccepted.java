package com.stargate.edd.application.events;


public class TransferAccepted extends BaseTransferEvent {

	private static final long serialVersionUID = 3562416992516448175L;
	
	private TransferRequestInfo info;
	
	public TransferAccepted() {}
	
	public TransferAccepted(Object obj, TransferRequestInfo info) {
		super(obj);
		this.info = info;
	}
	
	public TransferRequestInfo getInfo() {
		return info;
	}
}
