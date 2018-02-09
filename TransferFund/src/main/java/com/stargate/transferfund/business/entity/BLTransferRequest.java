package com.stargate.transferfund.business.entity;

import java.io.Serializable;

import com.stargate.transferfund.entity.TransactionType;

public class BLTransferRequest implements Serializable {

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

	public static BLTransferRequest buildDebitTransferRequest(BLTransaction txn) {
		BLTransferRequest blTransferRequest = new BLTransferRequest();
		// TODO assumption: sender is always the debitor
		blTransferRequest.setAccountNo(txn.getSenderDetails());
		blTransferRequest.setAmount(txn.getAmount());
		blTransferRequest.setTransactionType(TransactionType.DEBIT);
		return blTransferRequest;
	}

	@Override
	public String toString() {
		return "TransferRequest [accountNo =" + accountNo + "amount=" + amount + ", transactionType=" + transactionType
				+ "]";
	}
}
