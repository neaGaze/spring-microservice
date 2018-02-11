package com.stargate.ach.service;

import com.stargate.ach.business.entity.BLTransaction;
import com.stargate.ach.entity.Transaction;

public interface ACHService {

	void addTransaction(BLTransaction txn);

	Integer updateStatus(BLTransaction transactionWithStatus, String updatedStatus);
}
