package com.fitness.prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@EnableAutoConfiguration
@SpringBootApplication
public class FitnessPrototypeApplication {
	
	@Bean
	public ResourceBundleMessageSource messageSource() 
	{
	    ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
	    resource.setBasename("message");
	    return resource;
	}


	public static void main(String[] args) {
		SpringApplication.run(FitnessPrototypeApplication.class, args);
	}


}
