package com.aigallery.services;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aigallery.entities.User;
import com.aigallery.helpers.ResourceNotFoundException;
import com.aigallery.repository.UserRepo;

@Service
public class UserServiceIMPL implements UserService {

    private UserRepo userRepo;
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserServiceIMPL(UserRepo theUserRepo){
        this.userRepo = theUserRepo;
    }

    @Override
    public ResponseEntity<User> saveUser(User user) {

        String userId =  UUID.randomUUID().toString();
        user.setId(userId);
        user.setRole("ROLE_USER");
        userRepo.save(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> getUserByEmailAndPassword(String email, String password) {
        User tempUser = userRepo.findByEmailAndPassword(email, password);
        if(tempUser == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }else {
            return ResponseEntity.ok(tempUser);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        // Implementation here
        return null;
    }

    @Override
    public void deleteUser(String id) {
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found!"));
        userRepo.delete(user);
    }

    @Override
    public User getUserById(String id) {
        // Implementation here
        return null;
    }

    

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users;
    }

    @Override
    public User updateUser(User user) {
        User theUser = userRepo.findById(user.getId()).orElseThrow(()-> new ResourceNotFoundException("User Not Found!!"));
        // Update th user
        theUser.setName(user.getName());
        theUser.setEmail(user.getEmail());
        theUser.setPassword(user.getPassword());
        userRepo.save(theUser);
        return theUser;
    }

   
}
