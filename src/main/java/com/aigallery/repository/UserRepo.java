package com.aigallery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aigallery.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findById (long id);
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
