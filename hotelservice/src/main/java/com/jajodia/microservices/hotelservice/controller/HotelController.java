package com.jajodia.microservices.hotelservice.controller;

import com.jajodia.microservices.hotelservice.entity.Hotel;
import com.jajodia.microservices.hotelservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotelServices")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    // create hotel
    @PostMapping("/createHotel")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel savedHotel  = hotelService.createHotel(hotel);
        return new ResponseEntity<>(savedHotel,HttpStatus.CREATED);
    }
    //get all hotels
    @GetMapping("/getAllHotels")
    public ResponseEntity<List<Hotel>> fetchAllHotels(){
        List<Hotel> allHotels = hotelService.getAllHotels();
        return new ResponseEntity<>(allHotels,HttpStatus.OK);
    }
    //get hotel by Id
    @GetMapping("/getHotelByID/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){
        Hotel hotel = hotelService.getHotelById(hotelId);
        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }
}
