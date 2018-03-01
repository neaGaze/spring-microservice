package com.stargate.ach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/*USERSTORY-3
 * As a ACH, I want to receive a file/data showing all checks/transactions to 
 * be cleared for the benefit of
  debiting and crediting the corresponding accounts.
*/

@SpringBootApplication
public class AchApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AchApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AchApplication.class);
    }
}
