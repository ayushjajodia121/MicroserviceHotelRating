package com.jajodia.microservices.hotelservice.serviceimpl;

import com.jajodia.microservices.hotelservice.entity.Hotel;
import com.jajodia.microservices.hotelservice.exception.ResourceNotFoundException;
import com.jajodia.microservices.hotelservice.repository.HotelRepository;
import com.jajodia.microservices.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

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
        return hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Resource not found with given ID"));
    }
}
