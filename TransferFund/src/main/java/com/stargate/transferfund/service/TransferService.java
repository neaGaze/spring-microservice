package com.stargate.transferfund.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stargate.transferfund.entity.Bank;
import com.stargate.transferfund.entity.Transaction;

public interface TransferService {
	List<Bank> findAll();
	
	void dumpFlatFile(Transaction transaction);
}
