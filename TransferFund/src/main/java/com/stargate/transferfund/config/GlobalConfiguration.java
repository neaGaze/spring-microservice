package com.stargate.transferfund.config;

import javax.xml.ws.spi.http.HttpContext;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.web.client.RestTemplate;

import com.stargate.transferfund.service.TransferService;
import com.stargate.transferfund.service.TransferServiceImpl;

@Configuration
public class GlobalConfiguration {
	
	@Bean
    public TransferService configureDbService() {
        return new TransferServiceImpl();
    }
	
}
