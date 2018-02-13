package com.stargate.ach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stargate.ach.business.entity.BLTransaction;
import com.stargate.ach.entity.ACHTransaction;
import com.stargate.ach.exception.DBUpdateFailException;
import com.stargate.ach.exception.MulitpleDBRowEffectedException;
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
		ACHTransaction achTxn = txn.convertBLIntoDBEntity();
		repository.save(achTxn);
		txn.setTransactionId(achTxn.getTransaction_id());
		addTxnLogger.appendMessages("The transaction save completed with status " + achTxn.getStatus() +
				" and TransactionId: " + achTxn.getTransaction_id());
	}
	
	@Override
	public Integer updateStatus(BLTransaction transactionWithStatus, String updatedStatus) throws DBUpdateFailException, MulitpleDBRowEffectedException {
		Integer status = repository.updateStatusInDB(transactionWithStatus.getTransactionId(), updatedStatus);
		if(status <= 0) throw new DBUpdateFailException();
		if(status > 1) throw new MulitpleDBRowEffectedException();
		addTxnLogger.appendMessages("The transaction of Id " + transactionWithStatus.getTransactionId() + " is updated with the status " + status);
		return status;
	}
}
