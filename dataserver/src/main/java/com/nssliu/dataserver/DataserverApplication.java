package com.nssliu.dataserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DataserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataserverApplication.class, args);
	}

}
