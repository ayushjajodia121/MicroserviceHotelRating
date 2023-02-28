package com.jajodia.microservice.userservice.services;

import com.jajodia.microservice.userservice.entities.User;

import java.util.List;

public interface UserService {
    // create a user
    User saveUser(User user);

    //get all user
    List<User> getAllUsers();

    //get a single user
    User getUser(String userId);
}
