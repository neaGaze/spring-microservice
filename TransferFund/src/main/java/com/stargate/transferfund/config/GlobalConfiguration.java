package com.stargate.transferfund.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.stargate.transferfund.entity.Bank;
import com.stargate.transferfund.entity.TransferRequest;
import com.stargate.transferfund.service.TransferService;
import com.stargate.transferfund.service.TransferServiceImpl;
import com.stargate.transferfund.util.BankInformationExtractor;
import com.stargate.transferfund.validator.TransactionValidator;
import com.stargate.transferfund.validator.ValidatorRouter;

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
	
	@Bean
	public TransactionValidator transactionValidator() {
		return new TransactionValidator();
	}
	
	@Bean
	public TransferRequest transferRequest() {
		return new TransferRequest();
	}
	
	@Bean
	public ValidatorRouter validatorRouter() {
		return new ValidatorRouter();
	}
}
