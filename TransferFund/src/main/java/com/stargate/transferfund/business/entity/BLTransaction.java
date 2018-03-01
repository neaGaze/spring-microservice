package com.stargate.transferfund.business.entity;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.logging.BaseLogger;
import com.stargate.transferfund.logging.ConsoleLogger;

@Component
public class BLTransaction implements Serializable {

	private static final long serialVersionUID = -4450261208250531230L;

	private String transactionId;
	
	private String senderDetails;

	private String receiverDetails;

	private Double amount;

	private String status;

	@Autowired
	private BaseLogger bLTransactionLogger;
	
	@Autowired
	private BLTransaction blTransaction;
	
	public BLTransaction() {}

	public void decorateBLTransaction(Transaction transaction) {
		blTransaction.setSenderDetails(transaction.getSenderDetails().getAccountNumber());
		blTransaction.setReceiverDetails(transaction.getReceiverDetails().getAccountNumber());
		blTransaction.setAmount(transaction.getAmount());
		blTransaction.setStatus("In Progress"); // TODO need to figure out a way to remove the hard coded 'In Progress' status
		
		bLTransactionLogger.appendMessages("finished decorating BlTransaction...");
	}
	
	public String getSenderDetails() {
		return senderDetails;
	}

	public void setSenderDetails(String senderDetails) {
		this.senderDetails = senderDetails;
	}

	public String getReceiverDetails() {
		return receiverDetails;
	}

	public void setReceiverDetails(String receiverDetails) {
		this.receiverDetails = receiverDetails;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
