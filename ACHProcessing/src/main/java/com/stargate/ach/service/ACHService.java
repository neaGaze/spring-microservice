package com.stargate.ach.service;

import org.springframework.http.ResponseEntity;

import com.stargate.ach.entity.ResponseStatus;
import com.stargate.ach.entity.Transaction;

public interface ACHService {
	
	

	Transaction addTransaction(Transaction any);


	Integer updateStatus(Transaction txn,String anyString);

}
