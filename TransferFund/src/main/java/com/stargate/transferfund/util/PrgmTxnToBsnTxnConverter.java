package com.stargate.transferfund.util;

import com.stargate.transferfund.business.entity.BLTransaction;
import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.logging.BaseLogger;

/*********************************************************************************************************
 * This class converts the Program level Transaction Entity to Business level Transaction Entity 
 * before sending to through the JMS server
 ************************************************************************************************************/
public class PrgmTxnToBsnTxnConverter {

	public static BLTransaction convert(Transaction transaction, BaseLogger log) {
		
		BLTransaction blTransaction = new BLTransaction();
		blTransaction.setSenderDetails(transaction.getSenderDetails().getAccountNumber());
		blTransaction.setReceiverDetails(transaction.getReceiverDetails().getAccountNumber());
		blTransaction.setAmount(transaction.getAmount());
		blTransaction.setStatus("In Progress"); // TODO need to figure out a way to remove the hard coded 'In Progress' status
		return blTransaction;
	}
}
