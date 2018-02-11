package com.stargate.transferfund.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.stargate.transferfund.business.entity.BLTransaction;
import com.stargate.transferfund.controller.TransferController;
import com.stargate.transferfund.logging.BaseLogger;
import com.stargate.transferfund.logging.ConsoleLogger;
import com.stargate.transferfund.service.TransferServiceImpl;
import com.stargate.transferfund.util.JMSMessageDelayCalculatorUtil;

@Configuration
public class LoggingConfiguration {
	
	@Bean(name="initiateTransferLogger")
	public BaseLogger initiateTransferLogger() {
		return new ConsoleLogger(TransferController.class, "");
	}
	
	@Bean(name="executeTransferLogger")
	public BaseLogger executeTransferLogger() {
		return new ConsoleLogger(TransferController.class, "");
	}
	
	@Bean(name="updateUniDirTransferLogger")
	@DependsOn("executeTransferLogger")
	public BaseLogger updateUniDirTransferLogger(BaseLogger executeTransferLogger) {
		BaseLogger updateUniDirTransferLogger = new ConsoleLogger(TransferServiceImpl.class, "");
		executeTransferLogger.setSuccessor(updateUniDirTransferLogger);
		return updateUniDirTransferLogger;
	}
	
	@Bean(name="transferToJMSLogger")
	@DependsOn("initiateTransferLogger")
	public BaseLogger transferToJMSLogger(BaseLogger initiateTransferLogger) {
		BaseLogger transferToJMSLogger =  new ConsoleLogger(TransferServiceImpl.class, "");
		initiateTransferLogger.setSuccessor(transferToJMSLogger);
		return transferToJMSLogger;
	}
	
	@Bean(name="jmsDelayCalcLogger")
	@DependsOn("transferToJMSLogger")
	public BaseLogger jmsDelayCalcLogger(BaseLogger transferToJMSLogger) {
		BaseLogger jmsDelayCalcLogger =  new ConsoleLogger(JMSMessageDelayCalculatorUtil.class, "2. Calculating scheduled delayTime for JMS...");
		transferToJMSLogger.setSuccessor(jmsDelayCalcLogger);
		return jmsDelayCalcLogger;
	}
	
	@Bean(name="bLTransactionLogger")
	@DependsOn("transferToJMSLogger")
	public BaseLogger bLTransactionLogger(BaseLogger transferToJMSLogger) {
		BaseLogger bLTransactionLogger =  new ConsoleLogger(BLTransaction.class, "BlTransaction bean created...");
		transferToJMSLogger.setSuccessor(bLTransactionLogger);
		return bLTransactionLogger;
	}
	
	@Bean
	public JMSMessageDelayCalculatorUtil jmsMessageDelayCalculatorUtil() {
		return new JMSMessageDelayCalculatorUtil();
	}
	
	@Bean("blTransaction")
	public BLTransaction blTransaction() {return new BLTransaction();}
	/*
	@Bean(name="failedDBUpdateExceptionLogger")
	@DependsOn("updateUniDirTransferLogger")
	public BaseLogger failedDBUpdateExceptionLogger(BaseLogger updateUniDirTransferLogger) {
		BaseLogger failedDBUpdateException = new ConsoleLogger(FailedDBUpdateException.class, "");
		updateUniDirTransferLogger.setSuccessor(failedDBUpdateException);
		return failedDBUpdateException;
	}
	*/
}
