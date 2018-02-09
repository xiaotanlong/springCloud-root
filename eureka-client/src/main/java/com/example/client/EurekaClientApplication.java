package com.example.client;

import com.eureka.common.spi.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import java.util.Iterator;
import java.util.ServiceLoader;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);

		//java spi  test
		ServiceLoader<Log> serviceLoader = ServiceLoader.load(Log.class);
		Iterator<Log> iterator = serviceLoader.iterator();
		while (iterator.hasNext()) {
			Log log = iterator.next();
			log.execute("iiiiiiiiiiii");
		}
	}
}
