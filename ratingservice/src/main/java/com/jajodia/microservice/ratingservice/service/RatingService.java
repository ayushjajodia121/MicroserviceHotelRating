package com.jajodia.microservice.ratingservice.service;

import com.jajodia.microservice.ratingservice.entity.Rating;

import java.util.List;

public interface RatingService {
    //create rating
    Rating createRatings(Rating rating);
    //get all by users
    List<Rating> getAllRatingsByUsers(String userId);
    //get all by hotels
    List<Rating> getAllRatingsByHotels(String hotelId);
    //get all ratings
    List<Rating> getAllRatings();
}
