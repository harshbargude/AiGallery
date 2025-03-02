package com.aigallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aigallery.entities.User;

public interface UserRepo extends JpaRepository<User,String> {
    User getUserById (String id);
    User findByEmailAndPassword(String email, String password);
}
