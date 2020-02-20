package com.nssliu.restribboncustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
public class RestRibbonCustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestRibbonCustomerApplication.class, args);
	}
	@Bean

	@LoadBalanced

	RestTemplate restTemplate()

	{

		return new RestTemplate();

	}
}
