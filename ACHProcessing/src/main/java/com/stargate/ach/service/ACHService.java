package com.stargate.ach.service;

import com.stargate.ach.business.entity.BLTransaction;
import com.stargate.ach.entity.Transaction;

public interface ACHService {

	BLTransaction addTransaction(BLTransaction txn);

	Integer updateStatus(BLTransaction txn, String anyString);
}
