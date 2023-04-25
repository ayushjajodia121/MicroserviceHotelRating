package com.jajodia.microservice.userservice.impl;

import com.jajodia.microservice.userservice.entities.Hotel;
import com.jajodia.microservice.userservice.entities.Rating;
import com.jajodia.microservice.userservice.entities.User;
import com.jajodia.microservice.userservice.exceptions.ResourceNotFoundException;
import com.jajodia.microservice.userservice.repositories.UserRepository;
import com.jajodia.microservice.userservice.services.UserService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User saveUser(User user) {
        String sId = UUID.randomUUID().toString();
        user.setUserId(sId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @CircuitBreaker(name="ratingHotelServiceCircuitBreaker",fallbackMethod = "fetchUserByIdWithoutHotelRating")
//    @Retry(name = "ratingHotelServiceRetry",fallbackMethod = "fetchUserByIdWithoutHotelRating")
    @RateLimiter(name = "userRateLimiter",fallbackMethod = "userRateLimiter")
    public User getUser(String userId, String ratingsUrl, String hotelsUrl) {
        System.out.println("retry called");
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id not found!"));
        Rating[] ratingsFromRatingService = restTemplate.getForObject(ratingsUrl + "getByUserId/"+ userId, Rating[].class);
        List<Rating> ratingList = Arrays.stream(ratingsFromRatingService).collect(Collectors.toList());
        for(Rating rat:ratingList){
            String hotelId = rat.getHotelId();
            Hotel hotel = restTemplate.getForObject(hotelsUrl +"getHotelByID/"+ hotelId, Hotel.class);
            rat.setHotel(hotel);
        }
        user.setRatings(ratingList);
        return user;
    }
    private User fetchUserByIdWithoutHotelRating(String userId, String ratingsUrl, String hotelsUrl, ResourceAccessException ex){
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id not found!"));
        List<Rating> ratings = new ArrayList<>();
        Rating dummyRating = new Rating();
        dummyRating.setRemark("Rating service down");
        ratings.add(dummyRating);
        user.setRatings(ratings);
        return user;
    }

    private User fetchUserByIdWithoutHotelRating(String userId, String ratingsUrl, String hotelsUrl, CallNotPermittedException ex){
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id not found!"));
        List<Rating> ratings = new ArrayList<>();
        Rating dummyRating = new Rating();
        dummyRating.setRemark("Rating service down, pls try after some time");
        ratings.add(dummyRating);
        user.setRatings(ratings);
        return user;
    }
    private User userRateLimiter(String userId, String ratingsUrl, String hotelsUrl,Exception ex){
        User user = new User();
        user.setName("so many requests");
        return user;
    }
}
