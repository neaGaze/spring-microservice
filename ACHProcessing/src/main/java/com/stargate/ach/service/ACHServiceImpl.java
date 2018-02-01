package com.stargate.ach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.stargate.ach.entity.ResponseStatus;
import com.stargate.ach.entity.Transaction;
import com.stargate.ach.repository.*;
@Service
public class ACHServiceImpl implements ACHService {

	@Autowired
	ACHRepository repository;
	@Override
	public Transaction addTransaction( Transaction txn) {


		if (txn == null) {
			return null;
		}

		Transaction added = repository.save(txn);
		return added;
	}


	public Integer updateStatus(Transaction transactionWithStatus, String updatedStatus) {

		return repository.updateStatusInDB(transactionWithStatus.getId(),updatedStatus);


	}
}

