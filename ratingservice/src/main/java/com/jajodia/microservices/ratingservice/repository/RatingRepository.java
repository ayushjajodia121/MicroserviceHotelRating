package com.jajodia.microservices.ratingservice.repository;

import com.jajodia.microservices.ratingservice.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
    //find ratings by userID
    List<Rating> findByUserId(String userId);

    //find ratings by hotelId
    List<Rating> findByHotelId(String hotelId);
}
