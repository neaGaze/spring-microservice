package com.stargate.edd.application.peristence.mock;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import com.stargate.edd.application.entity.ACHTransaction;
import com.stargate.edd.application.entity.TransferRequestState;
import com.stargate.edd.application.events.BaseTransferEvent;
import com.stargate.edd.application.events.CreditCompleted;
import com.stargate.edd.application.events.CreditFailed;
import com.stargate.edd.application.events.CreditTxnPlaced;
import com.stargate.edd.application.events.DebitCompleted;
import com.stargate.edd.application.events.DebitFailed;
import com.stargate.edd.application.events.DebitTxnPlaced;
import com.stargate.edd.application.events.TransferAccepted;
import com.stargate.edd.application.events.TransferCompleted;
import com.stargate.edd.application.events.TransferRejected;
import com.stargate.edd.application.events.TransferRequestCreated;
import com.stargate.edd.application.events.TransferRequestInfo;

/***
 * This is the mock repository for `EventStore`. Ideally we want something like
 * NoSQL to store the events. This also contains the JMS Listener which are
 * trigerred internally from within the program
 ****/
public class TransferRequestRepoMock {

	// private Map<UUID, TransferRequestState> mockRepo = new ConcurrentHashMap<UUID, TransferRequestState>();

	@Autowired
	private ACHRepository repository;

	public TransferRequestState getMockRepo(UUID id) {
		return TransferRequestState.build(repository.findOne(id.toString()));
		//return mockRepo.get(id);
	}

	@JmsListener(destination = "${jms.queue.destination}", containerFactory = "myFactory")
	public void JMSlistener(BaseTransferEvent evt) {
/*
		if (evt instanceof TransferRequestCreated)
			this.apply((TransferRequestCreated) evt);
			
		if (evt instanceof TransferAccepted)
			this.apply((TransferAccepted) evt);
			
		if (evt instanceof TransferRejected)
			this.apply((TransferRejected) evt);
			
		if(evt instanceof DebitTxnPlaced) 
			this.apply((DebitTxnPlaced) evt);
		
		if(evt instanceof DebitCompleted) 
			this.apply((DebitCompleted) evt);
			
		if(evt instanceof DebitFailed) 
			this.apply((DebitFailed) evt);

		if(evt instanceof CreditTxnPlaced) 
			this.apply((CreditTxnPlaced) evt);

		if(evt instanceof CreditCompleted) 
			this.apply((CreditCompleted) evt);

		if(evt instanceof CreditFailed) 
			this.apply((CreditFailed) evt);
 	*/
	}

	public void applyCreateTranfer(TransferRequestInfo info) {
		/*
		System.out.println("\nTransferRequestCreated Receiver 2 destination: <" + info.getDestination() + ">");
		
		mockRepo.putIfAbsent(evt.getInfo().getId(), new TransferRequestState());
		mockRepo.get(evt.getInfo().getId()).place(evt.getInfo());
		mockRepo.forEach(
				(k, state) -> System.out.println("The list of events in NoSQL EventStore as : " + state.toString()));
		 */
		
		// save transaction in MySQL database
		TransferRequestState state = new TransferRequestState();
		state.place(info);
		ACHTransaction ach = ACHTransaction.convertFromInfo(state);
		System.out.println("ach before saving: " + ach.toString());
		repository.save(ach);
		repository.findAll().forEach(k -> System.out.println("The list of events in NoSQL EventStore as : " + ach.toString()));
	}

	public void applyTranferAccept(TransferRequestInfo info) {
		System.out.println("TransferAccepted Receiver 2 destination: <" + ">");
		//mockRepo.get(evt.getInfo().getId()).accept();
		ACHTransaction ach = repository.findOne(info.getId().toString());
		TransferRequestState state = TransferRequestState.build(ach);
		state.accept();
		updateRepoStatus(state);
	}

	public void applyTransferReject(UUID id) {
		System.out.println("TransferAccepted Receiver 2 destination: <" + ">");
		//mockRepo.get(evt.getId()).reject();
		ACHTransaction ach = repository.findOne(id.toString());
		TransferRequestState state = TransferRequestState.build(ach);
		state.reject();
		updateRepoStatus(state);
	}

	public void applyTranferComplete(UUID id) {
		System.out.println("TransferCompleted Receiver 2 destination: <" + ">");
		ACHTransaction ach = repository.findOne(id.toString());
		TransferRequestState state = TransferRequestState.build(ach);
		state.complete();
		updateRepoStatus(state);
	}
	
	public void applyDebitPlaced(TransferRequestInfo info) {
		//mockRepo.get(evnt.getInfo().getId()).debitPlaced();
		ACHTransaction ach = repository.findOne(info.getId().toString());
		TransferRequestState state = TransferRequestState.build(ach);
		state.debitPlaced();
		updateRepoStatus(state);
	}

	public void applyDebitCompleted(UUID id) {
		//mockRepo.get(evnt.getId()).debitComplete();
		ACHTransaction ach = repository.findOne(id.toString());
		TransferRequestState state = TransferRequestState.build(ach);
		state.debitComplete();
		updateRepoStatus(state);		
	}

	public void applyDebitFail(UUID id) {
		//mockRepo.get(evnt.getId()).debitFail();
		ACHTransaction ach = repository.findOne(id.toString());
		TransferRequestState state = TransferRequestState.build(ach);
		state.debitFail();
		updateRepoStatus(state);	
	}
	
	public void applyCreditPlaced(TransferRequestInfo info) {
		//mockRepo.get(evnt.getInfo().getId()).creditPlaced();
		ACHTransaction ach = repository.findOne(info.getId().toString());
		TransferRequestState state = TransferRequestState.build(ach);
		state.creditPlaced();
		updateRepoStatus(state);
	}

	public void applyCreditCompleted(UUID id) {
		//mockRepo.get(evnt.getId()).creditComplete();
		ACHTransaction ach = repository.findOne(id.toString());
		TransferRequestState state = TransferRequestState.build(ach);
		state.creditComplete();
		updateRepoStatus(state);
	}

	public void applyCreditFail(UUID id) {
		//mockRepo.get(evnt.getId()).creditFail();
		ACHTransaction ach = repository.findOne(id.toString());
		TransferRequestState state = TransferRequestState.build(ach);
		state.creditFail();
		updateRepoStatus(state);
	}
	
	public void applyCreditRollbackPlaced(UUID id) {
		//mockRepo.get(evnt.getInfo().getId()).debitPlaced();
		ACHTransaction ach = repository.findOne(id.toString());
		TransferRequestState state = TransferRequestState.build(ach);
		state.creditRollbackPlaced();
		updateRepoStatus(state);
	}

	public void applyCreditRollbackCompleted(UUID id) {
		//mockRepo.get(evnt.getInfo().getId()).debitPlaced();
		ACHTransaction ach = repository.findOne(id.toString());
		TransferRequestState state = TransferRequestState.build(ach);
		state.creditRollbackComplete();
		updateRepoStatus(state);
	}
	
	public void updateRepoStatus(TransferRequestState state) {
		repository.updateStatusInDB(state.getInfo().getId().toString(), state.getState().toString());
	}
}
