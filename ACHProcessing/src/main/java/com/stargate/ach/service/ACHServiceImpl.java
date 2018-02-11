package com.stargate.ach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stargate.ach.business.entity.BLTransaction;
import com.stargate.ach.logging.BaseLogger;
import com.stargate.ach.repository.*;


@Service
public class ACHServiceImpl implements ACHService {

	@Autowired
	private BaseLogger addTxnLogger;
	
	@Autowired
	ACHRepository repository;

	@Override
	public void addTransaction(BLTransaction txn) {
		if (txn == null) {
			addTxnLogger.appendMessages("BLTransaction object is null");
			return;
		}
		
		repository.save(txn.convertBLIntoDBEntity());
		addTxnLogger.appendMessages("The transaction save completed with status " + txn.getStatus() +
				" and TransactionId: " + txn.getTransactionId());
	}
	
	@Override
	public Integer updateStatus(BLTransaction transactionWithStatus, String updatedStatus) {
		Integer status = repository.updateStatusInDB(transactionWithStatus.getTransactionId(), updatedStatus);
		addTxnLogger.appendMessages("The transaction of Id " + transactionWithStatus.getTransactionId() + " is updated with the status " + status);
		return status;
	}
}
