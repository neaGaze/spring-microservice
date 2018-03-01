package com.stargate.edd.application;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jms.core.JmsTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.stargate.edd.application.broker.EventProducer;
import com.stargate.edd.application.peristence.mock.ACHRepository;
import com.stargate.edd.application.peristence.mock.TransferRequestRepoMock;
import com.stargate.edd.application.service.QueryService;
import com.stargate.edd.application.service.TransferCommandService;

@Configuration
public class GeneralConfiguration {
	
	@Bean
	public EventProducer eventProducer() {
		return new EventProducer();
	}
	
	@Bean
	public TransferCommandService transferCommandService() {
		return new TransferCommandService();
	}

	@Bean
	public QueryService queryService() {
		return new QueryService();
	}
	
	@Bean
	public TransferRequestRepoMock transferRequestRepoMock() {
		return new TransferRequestRepoMock();
	}
}
