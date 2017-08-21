package com.example.eurake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ExampleEurakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleEurakeApplication.class, args);
	}
}
