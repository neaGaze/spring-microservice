package com.stargate.edd2.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class EventDrivenTestRun2Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EventDrivenTestRun2Application.class, args);
	}
	
	/**
     * Used when run as WAR
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EventDrivenTestRun2Application.class);
    }

}
