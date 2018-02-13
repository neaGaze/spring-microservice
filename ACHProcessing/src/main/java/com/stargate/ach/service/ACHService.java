package com.stargate.ach.service;

import com.stargate.ach.business.entity.BLTransaction;
import com.stargate.ach.entity.Transaction;
import com.stargate.ach.exception.DBUpdateFailException;
import com.stargate.ach.exception.MulitpleDBRowEffectedException;

public interface ACHService {

	void addTransaction(BLTransaction txn);

	Integer updateStatus(BLTransaction transactionWithStatus, String updatedStatus) throws DBUpdateFailException, MulitpleDBRowEffectedException;
}
