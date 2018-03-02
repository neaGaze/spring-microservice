package com.stargate.statusquery.edd.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stargate.statusquery.edd.application.service.QueryService;
import com.stargate.statusquery.edd.application.service.QueryServiceImpl;

@Configuration
public class GlobalConfiguration {
	
	@Bean
	public QueryService queryService() {
		return new QueryServiceImpl();
	}
}
