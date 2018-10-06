package com.example.webstorecartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.example.webstorecartservice.serviceproxy")
public class WebStoreCartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebStoreCartServiceApplication.class, args);
	}
}
