package com.jajodia.microservice.ratingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // Add spring configuration behaviour to the config class
@EnableSwagger2 // Enabling Swagger Version 2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(generateAPIInfo())
                .pathMapping("/api/v1/ratingService")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jajodia.microservice.ratingservice.controller"))
                .build();
    }
    //Api information
    private ApiInfo generateAPIInfo() {
        return new ApiInfo("Rating Service", "Contains APIs for Rating Service", "1.0.0",
                "https://www.jajodia.com/", getContacts(), "", "", new ArrayList());
    }

    // Developer Contacts
    private Contact getContacts() {
        return new Contact("Ayush Jajodia", "", "ayushjajodia121@gmail.com");
    }
}