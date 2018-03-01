package com.stargate.edd.application.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stargate.edd.application.broker.EventProducer;
import com.stargate.edd.application.entity.TransferRequestState;
import com.stargate.edd.application.events.TransferAccepted;
import com.stargate.edd.application.events.TransferRequestCreated;
import com.stargate.edd.application.events.TransferRequestInfo;
import com.stargate.edd.application.peristence.mock.TransferRequestRepoMock;

/***
 * An service class
 *****/
@Service
public class TransferCommandService {

	@Autowired
	private EventProducer eventProducer;
	
	@Autowired
	private TransferRequestRepoMock repo;
	
	/**
	 * Create a Create event out of the command
	 ***/
	public void placeTransferRequest(TransferRequestInfo info) {
		eventProducer.publish(new TransferRequestCreated(this, info));
	}
	
	public void createdTranferRequest(TransferRequestInfo info) {
		repo.applyCreateTranfer(info);
	}
	
	public void acceptTransferRequest(UUID id) {
		TransferRequestInfo info = repo.getMockRepo(id).getInfo();
		eventProducer.publish(new TransferAccepted(this, info));
	}
	
	public void processTransferRequestAccepted(TransferRequestInfo info) {
		repo.applyTranferAccept(info);
	}
	
	public void rejectTransfer(UUID id) {
		repo.applyTransferReject(id);
	}
	
	public void processDebitPlaced(TransferRequestInfo info) {
		repo.applyDebitPlaced(info);
	}
	
	public void processDebitCompleted(UUID id) {
		repo.applyDebitCompleted(id);
	}
	
	public void processDebitFailed(UUID id) {
		repo.applyDebitFail(id);
	}
	
	public void processCreditPlaced(TransferRequestInfo info) {
		repo.applyCreditPlaced(info);
	}
	
	public void processCreditCompleted(UUID id) {
		repo.applyCreditCompleted(id);
	}
	
	public void processCreditFailed(UUID id) {
		repo.applyCreditFail(id);
		
	}
}
