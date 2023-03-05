package com.jajodia.microservices.hotelservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @Column(name = "hotel_id")
    private String hotelId;

    @Column(name = "hotel_name")
    private String name;

    @Column(name = "hotel_location")
    private String location;

    @Column(name = "hotel_about")
    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();

}
