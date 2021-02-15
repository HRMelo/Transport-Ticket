package com.hiberus.pricegenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsPricegeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPricegeneratorApplication.class, args);
	}

}
