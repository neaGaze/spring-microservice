package com.stargate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stargate.controller.GetBalanceController;
import com.stargate.logging.BaseLogger;
import com.stargate.logging.ConsoleLogger;

@Configuration
public class LoggingConfiguration {

	@Bean
	public BaseLogger controllerLogger() {
		return new ConsoleLogger(GetBalanceController.class, "");
	}
}
