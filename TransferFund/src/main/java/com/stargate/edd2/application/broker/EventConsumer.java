package com.stargate.edd2.application.broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.stargate.edd.application.events.BaseTransferEvent;
import com.stargate.edd.application.events.CreditTxnPlaced;
import com.stargate.edd.application.events.DebitTxnPlaced;
import com.stargate.edd.application.events.TransferRequestCreated;
import com.stargate.edd2.application.service.TransferFundService;

@Component
public class EventConsumer {

	@Autowired
	private TransferFundService service;


	@JmsListener(destination = "${jms.queue.destination}", containerFactory = "myFactory", subscription="${jms.queue.destination}")
	public void outerEventListener(BaseTransferEvent event) {
		if (event instanceof TransferRequestCreated) {
			this.apply((TransferRequestCreated) event);
		}

		if (event instanceof DebitTxnPlaced) {
			this.apply((DebitTxnPlaced) event);
		}

		if (event instanceof CreditTxnPlaced) {
			this.apply((CreditTxnPlaced) event);
		}
	}

	public void apply(TransferRequestCreated evt) {
		System.out.println("TransferRequestCreated Receiver 3 destination: <" + evt.getInfo().getDestination() + ">");

		// send the request back to the originating micro-service
		service.sendValidatedTransfer(evt.getInfo().getId());
	}

	public void apply(DebitTxnPlaced evt) {
		service.placeDebitCompleteEvent(evt.getInfo());
	}

	public void apply(CreditTxnPlaced evt) {
		service.placeCreditCompleteEvent(evt.getInfo());
	}
}
