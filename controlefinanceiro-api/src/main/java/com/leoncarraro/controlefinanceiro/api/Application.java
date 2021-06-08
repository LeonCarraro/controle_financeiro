package com.leoncarraro.controlefinanceiro.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(value = "com.leoncarraro.controlefinanceiro.api.config.property")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
