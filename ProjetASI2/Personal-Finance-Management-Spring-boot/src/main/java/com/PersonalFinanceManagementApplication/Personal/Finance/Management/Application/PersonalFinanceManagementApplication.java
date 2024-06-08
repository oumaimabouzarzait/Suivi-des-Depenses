package com.PersonalFinanceManagementApplication.Personal.Finance.Management.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PersonalFinanceManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalFinanceManagementApplication.class, args);
	}

}
