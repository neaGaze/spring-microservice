package com.stargate.status;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/*USERSTORY-4
 * As an originating or receiving Bank, 
 * I want to check the status of the transaction for the benefit of 
 * ensuring whether the transaction has been successful or not.
 * 
 * **/
@SpringBootApplication
public class GetStatusApplication  extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(GetStatusApplication.class, args);
	}
	
	/**
     * Used when run as WAR
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GetStatusApplication.class);
    }
}

