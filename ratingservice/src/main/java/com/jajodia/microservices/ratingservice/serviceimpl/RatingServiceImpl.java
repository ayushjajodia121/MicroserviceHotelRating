package com.jajodia.microservices.ratingservice.serviceimpl;

import com.jajodia.microservices.ratingservice.entity.Rating;
import com.jajodia.microservices.ratingservice.exception.ResourceNotFoundException;
import com.jajodia.microservices.ratingservice.repository.RatingRepository;
import com.jajodia.microservices.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRatings(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatingsByUsers(String userId) {
        List<Rating> ratingByUserId = ratingRepository.findByUserId(userId);
        if(ratingByUserId==null || ratingByUserId.size()==0){
            throw new ResourceNotFoundException("No rating found with given user id ");
        }
        return ratingByUserId;
    }

    @Override
    public List<Rating> getAllRatingsByHotels(String hotelId) {
        List<Rating> ratingByHotelId = ratingRepository.findByHotelId(hotelId);
        if(ratingByHotelId==null || ratingByHotelId.size()==0){
            throw new ResourceNotFoundException("No rating found with given hotel id ");
        }
        return ratingByHotelId;
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
}
