package com.stargate.ach.entity;

import javax.persistence.Entity;

public class TransferRequest {

	@Override
	public String toString() {
		return "TransferRequest [account=" + account + ", amount=" + amount + ", transactionType=" + transactionType
				+ "]";
	}

	protected Account account;

	protected Double amount;

	protected TransactionType transactionType;
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
	public static TransferRequest buildDebitTransferRequest(Transaction txn) {
		TransferRequest transferRequest = new TransferRequest();
		// TODO assumption: sender is always the debitor
		transferRequest.setAccount(txn.getSenderDetails()); 
		transferRequest.setAmount(txn.getAmount());
		transferRequest.setTransactionType(TransactionType.DEBIT);
		return transferRequest;
	}
	
}
