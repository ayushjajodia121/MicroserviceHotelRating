package com.jajodia.microservice.userservice.impl;

import com.jajodia.microservice.userservice.entities.User;
import com.jajodia.microservice.userservice.exceptions.ResourceNotFoundException;
import com.jajodia.microservice.userservice.repositories.UserRepository;
import com.jajodia.microservice.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository  userRepository;


    @Override
    public User saveUser(User user) {
        String sId = UUID.randomUUID().toString();
        user.setUserId(sId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id not found!"));
    }
}
