package com.stargate.ach.entity;

import java.io.Serializable;

import com.stargate.ach.business.entity.BLTransaction;

public class TransferRequest implements Serializable {

	private static final long serialVersionUID = 2723481588648225244L;

	protected String accountNo;

	protected Double amount;

	protected TransactionType transactionType;
	
	public String getAccount() {
		return accountNo;
	}

	public void setAccount(String accountNo) {
		this.accountNo = accountNo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
	public static TransferRequest buildDebitTransferRequest(BLTransaction txn) {
		TransferRequest transferRequest = new TransferRequest();
		// TODO assumption: sender is always the debitor
		transferRequest.setAccountNo(txn.getSenderDetails()); 
		transferRequest.setAmount(txn.getAmount());
		transferRequest.setTransactionType(TransactionType.DEBIT);
		return transferRequest;
	}
	

	public static TransferRequest buildCreditTransferRequest(BLTransaction txn) {
		TransferRequest transferRequest = new TransferRequest();
		// TODO assumption: sender is always the creditor
		transferRequest.setAccountNo(txn.getReceiverDetails()); 
		transferRequest.setAmount(txn.getAmount());
		transferRequest.setTransactionType(TransactionType.CREDIT);
		return transferRequest;
	}
	
	@Override
	public String toString() {
		return "TransferRequest [accountNo =" + accountNo + "amount=" + amount + ", transactionType=" + transactionType
				+ "]";
	}
}
