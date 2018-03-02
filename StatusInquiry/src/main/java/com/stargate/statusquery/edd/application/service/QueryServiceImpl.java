package com.stargate.statusquery.edd.application.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stargate.statusquery.edd.application.entity.ACHTransaction;
import com.stargate.statusquery.edd.application.entity.TransferRequestState;
import com.stargate.statusquery.edd.application.repository.TransactionRepository;

@Service
public class QueryServiceImpl implements QueryService {

	@Autowired
	private TransactionRepository repository;
	
	@Override
	public TransferRequestState getTransactionState(UUID id) {
		Optional<ACHTransaction> transaction = Optional.of(repository.findOne(id.toString()));
		
		transaction.ifPresent(trx -> TransferRequestState.build(transaction.get()));
		//transaction.orElseThrow(exp -> new TransactionDoesNotExistException());
		return TransferRequestState.build(transaction.get());
	}

}
