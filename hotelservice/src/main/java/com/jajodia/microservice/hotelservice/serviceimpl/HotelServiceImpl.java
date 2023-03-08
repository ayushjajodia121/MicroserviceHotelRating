package com.jajodia.microservice.hotelservice.serviceimpl;

import com.jajodia.microservice.hotelservice.entity.Rating;
import com.jajodia.microservice.hotelservice.externals.services.RatingService;
import com.jajodia.microservice.hotelservice.repository.HotelRepository;
import com.jajodia.microservice.hotelservice.entity.Hotel;
import com.jajodia.microservice.hotelservice.exception.ResourceNotFoundException;
import com.jajodia.microservice.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RatingService ratingService;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Resource not found with given ID"));
            List<Rating> ratingsByHotelId = ratingService.getRatingsByHotelId(hotelId);
            if(ratingsByHotelId.size()!=0){
                hotel.setRatings(ratingsByHotelId);
            }

        return hotel;
    }
}
