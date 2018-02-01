package com.stargate.transferfund.entity;

public class DebitTransaction extends TransferRequest {

	public DebitTransaction() {
		//this.receiverDetails = null;
		this.transactionType = TransactionType.DEBIT;
	}
}
