package com.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class ApigatewayApplication {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

}
