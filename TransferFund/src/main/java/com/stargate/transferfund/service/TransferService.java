package com.stargate.transferfund.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stargate.transferfund.entity.Bank;
import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.entity.TransferRequest;
import com.stargate.transferfund.exception.FailedDBUpdateException;
import com.stargate.transferfund.exception.InvalidRequestException;

public interface TransferService {
	List<Bank> findAll();
	
	boolean transfertoJMS(Transaction transaction);
	
	void updateUniTransfer(TransferRequest transferRequest) throws FailedDBUpdateException;
}
