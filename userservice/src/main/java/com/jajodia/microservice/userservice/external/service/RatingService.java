package com.jajodia.microservice.userservice.external.service;

import com.jajodia.microservice.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {
    //get
    //post
    @PostMapping("/api/v1/ratingService/createRating")
    Rating create(Rating rating);
    //put
    @PutMapping("/api/v1/ratingService/updateRating/{ratingId}")
    Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);


}
