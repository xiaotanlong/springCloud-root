package com.enjoy.cap12;

import com.enjoy.cap9.bean.Moon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.enjoy.cap12.processor")
public class Cap12MainConfig {
	@Bean
	public Moon getMoon(){
		return new Moon();
	}
}
