package com.example.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ExampleDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(ExampleDemo1Application.class, args);
	}
}
