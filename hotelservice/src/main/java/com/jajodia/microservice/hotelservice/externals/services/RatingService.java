package com.jajodia.microservice.hotelservice.externals.services;

import com.jajodia.microservice.hotelservice.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE",url = "http://localhost:8083")
public interface RatingService {
    @GetMapping("/api/v1/ratingService/getByHotelId/{hotelId}")
    List<Rating> getRatingsByHotelId(@PathVariable String hotelId);
}
