package com.jajodia.microservice.userservice;

import com.jajodia.microservice.userservice.entities.Rating;
import com.jajodia.microservice.userservice.external.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserserviceApplicationTests {

	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

	@Test
	void createRating(){
		Rating rating = Rating.builder().rating(1).hotelId("").userId("").remark("good one bro").build();
		Rating savedRating = ratingService.create(rating);
		System.out.println("new rating created");
	}
}
