package com.aigallery.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.aigallery.entities.User;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(String id);
    ResponseEntity<User> saveUser(User user);
    User updateUser(User user);
    void deleteUser(String id);
    User getUserByEmail(String email);
    ResponseEntity<User> getUserByEmailAndPassword(String email, String password);
    
}
