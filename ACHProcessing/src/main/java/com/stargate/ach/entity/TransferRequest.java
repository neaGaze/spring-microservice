package com.stargate.ach.entity;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.stargate.ach.business.entity.BLTransaction;

@Component
public class TransferRequest implements Serializable {

	private static final long serialVersionUID = 2723481588648225244L;

	protected String accountNo;

	protected Double amount;

	protected TransactionType transactionType;
	
	@Autowired
	@Qualifier("debitTransferRequest")
	private TransferRequest debitTransferRequest;

	@Autowired
	@Qualifier("creditTransferRequest")
	private TransferRequest creditTransferRequest;
	
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
	
	public void buildDebitTransferRequest(BLTransaction txn) {
		//TransferRequest transferRequest = new TransferRequest();
		// TODO assumption: sender is always the debitor
		debitTransferRequest.setAccountNo(txn.getSenderDetails()); 
		debitTransferRequest.setAmount(txn.getAmount());
		debitTransferRequest.setTransactionType(TransactionType.DEBIT);
		//return transferRequest;
	}
	

	public void buildCreditTransferRequest(BLTransaction txn) {
		//TransferRequest transferRequest = new TransferRequest();
		// TODO assumption: sender is always the creditor
		creditTransferRequest.setAccountNo(txn.getReceiverDetails()); 
		creditTransferRequest.setAmount(txn.getAmount());
		creditTransferRequest.setTransactionType(TransactionType.CREDIT);
		//return transferRequest;
	}
	
	@Override
	public String toString() {
		return "TransferRequest [accountNo =" + accountNo + "amount=" + amount + ", transactionType=" + transactionType
				+ "]";
	}
}
