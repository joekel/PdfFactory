
package com.pdf.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.pdf.service.ConverterImpl;
import com.pdf.service.TemplateDataTransformerImpl;
import com.pdf.service.TestDataProviderImpl;
import com.pdf.spi.Converter;
import com.pdf.spi.TemplateDataTransformer;
import com.pdf.spi.TestDataProvider;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}

	@Bean
	public Converter getConverter(){
		return new ConverterImpl();
	}
	
	@Bean
	public TemplateDataTransformer getTemplateTransformer(){
		return new TemplateDataTransformerImpl();
	}
	
	@Bean
	public TestDataProvider getDataProvider(){
		return new TestDataProviderImpl();
	}
}
