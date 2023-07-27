package com.configmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigmanagerApplication.class, args);
	}

}
