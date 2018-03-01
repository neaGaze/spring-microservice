package com.stargate.transferfund.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.stargate.transferfund.business.entity.BLTransaction;
import com.stargate.transferfund.entity.Transaction;

/*********************************************************************************************************
 * This class converts the Program level Transaction Entity to Business level Transaction Entity 
 * before sending to through the JMS server
 ************************************************************************************************************/
public class PrgmTxnToBsnTxnConverter {

	@Autowired
	private BLTransaction blTransaction;
	
	public BLTransaction convert(Transaction transaction) {
		
		//BLTransaction blTransaction = new BLTransaction();
		blTransaction.setTransactionId("-1");  // dummy id because we still haven't saved in db
		blTransaction.setSenderDetails(transaction.getSenderDetails().getAccountNumber());
		blTransaction.setReceiverDetails(transaction.getReceiverDetails().getAccountNumber());
		blTransaction.setAmount(transaction.getAmount());
		blTransaction.setStatus("In Progress"); // TODO need to figure out a way to remove the hard coded 'In Progress' status
		return blTransaction;
	}
}
