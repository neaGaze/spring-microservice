package com.stargate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

import com.stargate.GetBalanceApplication;

@SpringBootApplication
public class GetBalanceApplication extends ServletInitializer {
	
			public static void main(String[] args) {
		 
		SpringApplication.run(GetBalanceApplication.class, args);
	}
}
