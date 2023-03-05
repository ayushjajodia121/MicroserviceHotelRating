package com.jajodia.microservices.ratingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient                          // this annotation is not required anymore, Simply adding spring-cloud-starter-netflix-eureka-client to dependencies will enable the client. If you want to disable it, set the property value of eureka.client.enabled to false.
public class RatingserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingserviceApplication.class, args);
	}

}
