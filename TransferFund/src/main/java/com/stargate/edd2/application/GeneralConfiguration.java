package com.stargate.edd2.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stargate.edd2.application.broker.EventProducer;
import com.stargate.edd2.application.service.TransferFundService;

@Configuration
public class GeneralConfiguration {
	
	@Bean
	public EventProducer eventProducer() {
		return new EventProducer();
	}
	
	@Bean
	public TransferFundService transferFundService() {
		return new TransferFundService();
	}
}
