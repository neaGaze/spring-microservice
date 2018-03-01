package com.stargate.edd.application.broker;

import javax.jms.DeliveryMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.stargate.edd.application.events.BaseTransferEvent;

@Component
public class EventProducer {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${jms.queue.destination}")
	String destinationQueue;

	public void publish(BaseTransferEvent event) {
		//jmsTemplate.setDeliveryDelay(1000);
		jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
		jmsTemplate.setPubSubDomain(true);
		jmsTemplate.convertAndSend(destinationQueue, event);
		System.out.println("jms messsage sent at queue: " + destinationQueue);
	}
	
	
}
