package com.stargate.transferfund.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.stargate.transferfund.service.TransferService;
import com.stargate.transferfund.service.TransferServiceImpl;

@Configuration
public class GlobalConfiguration {

	@Bean
	public TransferService configureDbService() {
		return new TransferServiceImpl();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder().build();
	}
}
