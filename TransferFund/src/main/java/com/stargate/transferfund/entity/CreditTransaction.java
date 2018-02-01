package com.stargate.transferfund.entity;

public class CreditTransaction extends TransferRequest {
	
	public CreditTransaction() {
		this.transactionType = TransactionType.CREDIT;
	}
}
