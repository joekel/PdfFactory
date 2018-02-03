
package com.pdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
	//test auto build jenkins
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}

}
