package de.mameie.databasemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DatabasemanagerApplication {

	/*
	//TODO: https://www.w3schools.com/sql/sql_update.asp
	 */

	public static void main(String[] args) {
		SpringApplication.run(DatabasemanagerApplication.class, args);
	}

}
