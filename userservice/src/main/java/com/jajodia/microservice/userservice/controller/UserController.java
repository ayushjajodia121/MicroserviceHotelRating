package com.jajodia.microservice.userservice.controller;

import com.jajodia.microservice.userservice.entities.User;
import com.jajodia.microservice.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userServices")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${ratingservice.url}")
    private String ratingsUrl;

    @Value("${hotelservice.url}")
    private String getHotelByIdUrl;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
        User fetchedUser = userService.getUser(userId, ratingsUrl, getHotelByIdUrl);
        return ResponseEntity.ok(fetchedUser);
    }
}
