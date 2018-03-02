package com.stargate.statusquery.edd.application.service;

import java.util.UUID;

import com.stargate.statusquery.edd.application.entity.TransferRequestState;


public interface QueryService {
	
	public TransferRequestState getTransactionState(UUID id);
}
