package com.example.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard//启用Hystrix Dashboard功能
@SpringBootApplication
public class EurekaHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaHystrixDashboardApplication.class, args);
	}

}
