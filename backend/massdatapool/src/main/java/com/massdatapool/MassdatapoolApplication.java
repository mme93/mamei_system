package com.massdatapool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MassdatapoolApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(MassdatapoolApplication.class);
	private static final Logger LOGGER_INFO = LoggerFactory.getLogger("com.example.info");

	public static void main(String[] args) {
		SpringApplication.run(MassdatapoolApplication.class, args);
		LOGGER_INFO.info("This is information");
		LOGGER.debug("This is debug");
		LOGGER.error("This is error");
	}

}
