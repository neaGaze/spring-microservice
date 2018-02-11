package com.stargate.ach.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stargate.ach.controller.ACHController;
import com.stargate.ach.entity.TransferRequest;
import com.stargate.ach.logging.BaseLogger;
import com.stargate.ach.logging.ConsoleLogger;
import com.stargate.ach.service.ACHService;

@Configuration
public class LoggingConfiguration {

	@Bean(name="debitTransferRequest")
	public TransferRequest debitTransferRequest() {
		TransferRequest debitTransferRequest = new TransferRequest();
		return debitTransferRequest;	
	}

	@Bean(name="creditTransferRequest")
	public TransferRequest creditTransferRequest() {
		TransferRequest creditTransferRequest = new TransferRequest();
		return creditTransferRequest;	
	}

	@Bean(name="persistLogger")
	public BaseLogger persistLogger() {
		BaseLogger persistLogger =  new ConsoleLogger(ACHController.class, "");
		return persistLogger;
	}
	
	@Bean(name="debitTransferRequestLogger")
	public BaseLogger debitTransferRequestLogger(BaseLogger persistLogger) {
		BaseLogger debitTransferRequestLogger =  new ConsoleLogger(TransferRequest.class, "");
		persistLogger.setSuccessor(debitTransferRequestLogger);
		return debitTransferRequestLogger;
	}

	@Bean(name="creditTransferRequestLogger")
	public BaseLogger creditTransferRequestLogger(BaseLogger persistLogger) {
		BaseLogger creditTransferRequestLogger =  new ConsoleLogger(TransferRequest.class, "");
		persistLogger.setSuccessor(creditTransferRequestLogger);
		return creditTransferRequestLogger;
	}
	
	// for persist Logging
	@Bean
	public BaseLogger addTxnLogger(BaseLogger persistLogger) {
		BaseLogger addTxnLogger =  new ConsoleLogger(ACHService.class, "");
		persistLogger.setSuccessor(addTxnLogger);
		return addTxnLogger;
	}
}
