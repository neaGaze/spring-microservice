package com.stargate.edd.application.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stargate.edd.application.entity.TransferRequestState;
import com.stargate.edd.application.peristence.mock.TransferRequestRepoMock;

@Service
public class QueryService {

	@Autowired
	private TransferRequestRepoMock repository;
	
	public TransferRequestState getInfo(UUID id) {
		return repository.getMockRepo(id);}
}
