package com.example.client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


@EnableDiscoveryClient //注解用来将当前应用加入到服务治理体系中。
@SpringBootApplication
public class EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);

		//java spi  test
		/*ServiceLoader<Log> serviceLoader = ServiceLoader.load(Log.class);
		Iterator<Log> iterator = serviceLoader.iterator();
		while (iterator.hasNext()) {
			Log log = iterator.next();
			log.execute("iiiiiiiiiiii");
		}*/
	}
}
