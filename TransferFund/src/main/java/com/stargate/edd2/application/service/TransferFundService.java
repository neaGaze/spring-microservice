package com.stargate.edd2.application.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stargate.edd.application.events.CreditCompleted;
import com.stargate.edd.application.events.CreditFailed;
import com.stargate.edd.application.events.CreditTxnPlaced;
import com.stargate.edd.application.events.DebitCompleted;
import com.stargate.edd.application.events.DebitFailed;
import com.stargate.edd.application.events.DebitTxnPlaced;
import com.stargate.edd.application.events.RequestValidatedEvent;
import com.stargate.edd.application.events.TransferRequestInfo;
import com.stargate.edd2.application.broker.EventProducer;
import com.stargate.edd2.application.exception.InsufficientFundException;
import com.stargate.edd2.application.exception.NonAtomicUpdateException;
import com.stargate.edd2.application.repository.BankRepository;

@Service
public class TransferFundService {

	@Autowired
	private EventProducer eventProducer;

	@Autowired
	private BankRepository repo;

	public void sendValidatedTransfer(UUID id) {
		// perform validation
		// TODO

		eventProducer.publish(new RequestValidatedEvent(id));
	}

	public void placeDebitEvent(TransferRequestInfo info) {
		eventProducer.publish(new DebitTxnPlaced(info));
	}

	public void placeCreditEvent(TransferRequestInfo info) {
		eventProducer.publish(new CreditTxnPlaced(info));
	}

	public void placeDebitCompleteEvent(TransferRequestInfo info) {
		Integer rowsEffected = repo.debitBankBalance(info.getFrom(), info.getAmount());
		try {
			validate(rowsEffected);
		} catch (NonAtomicUpdateException | InsufficientFundException e) {
			eventProducer.publish(new DebitFailed(info.getId(), e.getMessage()));
		}
		
		eventProducer.publish(new DebitCompleted(info.getId()));
	}

	public void placeCreditCompleteEvent(TransferRequestInfo info) {
		Integer rowsEffected = repo.creditBankBalance(info.getDestination(), info.getAmount());
		try {
			validate(rowsEffected);
		} catch (NonAtomicUpdateException | InsufficientFundException e) {
			eventProducer.publish(new CreditFailed(info.getId(), e.getMessage()));
		} 
		
		eventProducer.publish(new CreditCompleted(info.getId()));
	}

	public void validate(Integer dbRowsUpdated) throws InsufficientFundException, NonAtomicUpdateException {
		if (dbRowsUpdated.intValue() == 0)
			throw new InsufficientFundException();
		
		if (dbRowsUpdated.intValue() > 1)
			throw new NonAtomicUpdateException();
	}
}
