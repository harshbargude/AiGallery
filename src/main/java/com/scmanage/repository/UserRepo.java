package com.scmanage.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scmanage.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,String>{
    //expra methodes (custom methods)
    //custom quary methodes 

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String password);
}
