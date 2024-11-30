package com.scmanage.services;

import java.util.List;
import java.util.Optional;


import com.scmanage.entities.User;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String userId);
    Optional<User> updateUser(User user);
    void deleteUser(String userId);
    boolean isUserExit(String userId);
    boolean isUserExistByEmailId(String email);
    List<User> getAllUsers(); 
    User getUserByEmail(String email);
}
