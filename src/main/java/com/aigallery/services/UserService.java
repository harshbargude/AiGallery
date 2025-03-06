package com.aigallery.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.aigallery.entities.User;
import com.aigallery.web.dto.forms.UserForm;

public interface UserService {

    String saveUser(UserForm userDTO);
    // List<User> getAllUsers();
    // User getUserById(String id);
    // User updateUser(User user);
    // void deleteUser(String id);
    // User getUserByEmail(String email);
    // ResponseEntity<User> getUserByEmailAndPassword(String email, String password);
    
}
