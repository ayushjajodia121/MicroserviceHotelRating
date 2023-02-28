package com.jajodia.microservices.hotelservice.repository;

import com.jajodia.microservices.hotelservice.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,String> {
}
