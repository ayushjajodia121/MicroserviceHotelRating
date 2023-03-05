package com.jajodia.microservice.userservice.impl;

import com.jajodia.microservice.userservice.entities.Hotel;
import com.jajodia.microservice.userservice.entities.Rating;
import com.jajodia.microservice.userservice.entities.User;
import com.jajodia.microservice.userservice.exceptions.ResourceNotFoundException;
import com.jajodia.microservice.userservice.repositories.UserRepository;
import com.jajodia.microservice.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public User getUser(String userId, String ratingsUrl, String hotelsUrl) {
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
}
