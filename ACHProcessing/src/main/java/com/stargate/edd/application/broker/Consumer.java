package com.stargate.edd.application.broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.stargate.edd.application.events.BaseTransferEvent;
import com.stargate.edd.application.events.CreditCompleted;
import com.stargate.edd.application.events.CreditFailed;
import com.stargate.edd.application.events.CreditTxnPlaced;
import com.stargate.edd.application.events.DebitCompleted;
import com.stargate.edd.application.events.DebitFailed;
import com.stargate.edd.application.events.DebitTxnPlaced;
import com.stargate.edd.application.events.RequestValidatedEvent;
import com.stargate.edd.application.events.TransferAccepted;
import com.stargate.edd.application.events.TransferRejected;
import com.stargate.edd.application.events.TransferRequestCreated;
import com.stargate.edd.application.service.TransferCommandService;

/****
 * Listens for events triggered by external causes (Eg: another microservice)
 ****/
@Component
public class Consumer /*implements ApplicationListener<BaseTransferEvent>*/{

	@Autowired
	private TransferCommandService service;
	/*
	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;
	*/
	@JmsListener(destination = "${jms.queue.destination}", containerFactory = "myFactory")
	public void receiveCreate(BaseTransferEvent evt) {
		
		//TransferRequestCreated transferCreatedEvent = (TransferRequestCreated) evt;
		//applicationEventPublisher.publishEvent(evt);

		if (evt instanceof TransferRequestCreated)
			this.apply((TransferRequestCreated) evt);
		
		if(evt instanceof RequestValidatedEvent) 
			this.apply((RequestValidatedEvent) evt);

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
	}
	
/*
	@Override
	public void onApplicationEvent(BaseTransferEvent event) {
		System.out.println("Event listener right here...");
	}*/	
	

	public void apply(TransferRequestCreated evt) {
		service.createdTranferRequest(evt.getInfo());
	}
	
	public void apply(RequestValidatedEvent requestValidatedEvent) {
		//System.out.println("RequestValidatedEvent Receiver 1 destination: <" + ">");
		service.acceptTransferRequest(requestValidatedEvent.getId());
	}

	public void apply(TransferAccepted evt) {
		service.processTransferRequestAccepted(evt.getInfo());
	}
	
	public void apply(TransferRejected evt) {
		service.rejectTransfer(evt.getId());
	}
	
	public void apply(DebitTxnPlaced evnt) {
		service.processDebitPlaced(evnt.getInfo());
	}
	
	public void apply(DebitCompleted evnt) {
		service.processDebitCompleted(evnt.getId());
	}
	
	public void apply(DebitFailed evnt) {
		service.processDebitFailed(evnt.getId());
	}
	
	public void apply(CreditTxnPlaced evnt) {
		service.processCreditPlaced(evnt.getInfo());
	}
	
	public void apply(CreditCompleted evnt) {
		service.processCreditCompleted(evnt.getId());
	}

	public void apply(CreditFailed evnt) {
		service.processCreditFailed(evnt.getId());
	}
}
