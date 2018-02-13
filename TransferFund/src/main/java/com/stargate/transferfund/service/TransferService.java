package com.stargate.transferfund.service;

import com.stargate.transferfund.business.entity.BLTransferRequest;
import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.exception.FailedDBUpdateException;

public interface TransferService {
	//List<Bank> findAll();
	
	void transfertoJMS(Transaction transaction);
	
	void updateUniTransfer(BLTransferRequest transferRequest) throws FailedDBUpdateException;
	
	boolean checkIfValid(Transaction transaction);
}
