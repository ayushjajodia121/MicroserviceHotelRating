package com.jajodia.microservices.ratingservice.controller;

import com.jajodia.microservices.ratingservice.entity.Rating;
import com.jajodia.microservices.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ratingService")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    //create a rating
    @PostMapping("/createRating")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating savedRating = ratingService.createRatings(rating);
        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }

    //fetch Rating by hotelID
    @GetMapping("/getByHotelId/{hotelId}")
    public ResponseEntity<List<Rating>> getByHotelId(@PathVariable String hotelId){
        List<Rating> allRatingsByHotels = ratingService.getAllRatingsByHotels(hotelId);
        return new ResponseEntity<>(allRatingsByHotels,HttpStatus.OK);
    }

    //fetch rating by userId
    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<Rating>> getByUserId(@PathVariable String userId){
        List<Rating> allRatingsByUsers = ratingService.getAllRatingsByUsers(userId);
        return new ResponseEntity<>(allRatingsByUsers,HttpStatus.OK);
    };

    //fetch all ratings
    @GetMapping("/getAllRatings")
    public ResponseEntity<List<Rating>> getAllRatings(){
        List<Rating> allRatings = ratingService.getAllRatings();
        return new ResponseEntity<>(allRatings,HttpStatus.OK);
    }
}
