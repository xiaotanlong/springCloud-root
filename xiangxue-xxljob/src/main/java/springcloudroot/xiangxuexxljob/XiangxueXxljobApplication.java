package springcloudroot.xiangxuexxljob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "springcloudroot.xiangxuexxljob.*")
public class XiangxueXxljobApplication {

	public static void main(String[] args) {
		SpringApplication.run(XiangxueXxljobApplication.class, args);
	}

}

