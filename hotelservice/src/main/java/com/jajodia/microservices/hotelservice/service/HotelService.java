package com.jajodia.microservices.hotelservice.service;

import com.jajodia.microservices.hotelservice.entity.Hotel;

import java.util.List;

public interface HotelService {

    // create hotels
    Hotel createHotel(Hotel hotel);

    //get all hotels
    List<Hotel> getAllHotels();

    //get single hotel
    Hotel getHotelById(String hotelId);
}
