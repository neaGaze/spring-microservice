package com.stargate.edd.application.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.stargate.edd.application.broker.EventProducer;
import com.stargate.edd.application.events.CreditRollbackPlaced;
import com.stargate.edd.application.events.DebitRollbackPlaced;
import com.stargate.edd.application.events.TransferAccepted;
import com.stargate.edd.application.events.TransferCompleted;
import com.stargate.edd.application.events.TransferRejected;
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
	
	@Value("${stargate.rollback.fail-timeout}")
	private String ROLLBACK_FAIL_TIMEOUT;
	
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
	
	public void rejectTransfer(UUID id, String reason) {
		repo.applyTransferReject(id);
	}
	
	public void completeTransfer(UUID id) {
		repo.applyTranferComplete(id);
	}
	
	public void processDebitPlaced(TransferRequestInfo info) {
		repo.applyDebitPlaced(info);
	}
	
	public void processDebitCompleted(UUID id) {
		repo.applyDebitCompleted(id);
	}
	
	public void processDebitFailed(UUID id) {
		repo.applyDebitFail(id);
		TransferRequestInfo info = repo.getMockRepo(id).getInfo();
		eventProducer.publish(new DebitRollbackPlaced(info));
	}
	
	public void processCreditPlaced(TransferRequestInfo info) {
		repo.applyCreditPlaced(info);
	}
	
	public void processCreditCompleted(UUID id) {
		repo.applyCreditCompleted(id);
		eventProducer.publish(new TransferCompleted(id));
	}
	
	public void processCreditFailed(UUID id) {
		repo.applyCreditFail(id);
		TransferRequestInfo info = repo.getMockRepo(id).getInfo();
		eventProducer.publish(new CreditRollbackPlaced(info));
	}
	
	public void processDebitRollbackPlaced(TransferRequestInfo info) {
		eventProducer.publish(new TransferRejected(info.getId(), "Failed Debit opearation: "));
	}
	
	public void processCreditRollbackPlaced(TransferRequestInfo info) {
		repo.applyCreditRollbackPlaced(info.getId());
	}
	
	public void processCreditRollbackComplete(UUID id) {
		repo.applyCreditRollbackCompleted(id);
		eventProducer.publish(new TransferRejected(id, "Failed Credit opearation"));
	}
	
	public void processCreditRollbackFail(UUID id) {
		// This has to persist anyhow because if it doesn't persist, the system is in inconsistent state
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		        		TransferRequestInfo info = repo.getMockRepo(id).getInfo();
		        		eventProducer.publish(new CreditRollbackPlaced(info));
		            }
		        }, 
		        Long.valueOf(ROLLBACK_FAIL_TIMEOUT) 
		);
	}
}
