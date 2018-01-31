package com.stargate.transferfund.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import com.stargate.transferfund.entity.Transaction;

@Component
public class TransactionReceiver {
/*
	@JmsListener(destination = "${jms.queue.destination}", containerFactory = "myFactory")
	public void receiveMessage(Transaction transaction) {
		System.out.println("Received <" + transaction.getAmount() + ">");
		//transactionRepository.save(transaction);
	}	*/
}
