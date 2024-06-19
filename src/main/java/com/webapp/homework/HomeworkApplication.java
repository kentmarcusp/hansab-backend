package com.webapp.homework;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages = "com.webapp.homework")
public class HomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}

	@Bean
	@Autowired
	CommandLineRunner commandLineRunner() {
		return args -> {
		};
	}


}
