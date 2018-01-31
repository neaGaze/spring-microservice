package com.stargate.transferfund.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stargate.transferfund.entity.Bank;
import com.stargate.transferfund.entity.Transaction;
import com.stargate.transferfund.exception.InvalidRequestException;

public interface TransferService {
	List<Bank> findAll();
	
	boolean dumpFlatFile(Transaction transaction) /*throws InvalidRequestException*/;
}
