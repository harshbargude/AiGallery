package com.aigallery.services.IMPL;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aigallery.entities.Role;
import com.aigallery.entities.User;
import com.aigallery.helpers.ResourceNotFoundException;
import com.aigallery.repository.RoleRepository;
import com.aigallery.repository.UserRepo;
import com.aigallery.services.UserService;
import com.aigallery.web.dto.forms.UserForm;

@Service
public class UserServiceIMPL implements UserService {

    private UserRepo userRepo;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder; //use PasswordEncoder insted


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserServiceIMPL(PasswordEncoder theBCryptPasswordEncoder,UserRepo theUserRepo, RoleRepository theRoleRepository) {
        this.roleRepository = theRoleRepository;
        this.userRepo = theUserRepo;
        this.passwordEncoder = theBCryptPasswordEncoder;
    }

    @Override
    public String saveUser(UserForm userDTO) {
        Role defaultRole = roleRepository.findByRole("ROLE_USER")
        .orElseGet(() -> roleRepository.save(new Role("ROLE_USER"))); // Get existing role or create one

        User user = new User(
            userDTO.getFirstName(),
            userDTO.getLastName(),
            userDTO.getEmail(),
            passwordEncoder.encode(userDTO.getPassword()), //remember this friend haha
            true,
            Arrays.asList(defaultRole));
        userRepo.save(user);
        return "User Saved Successfully!";
    }

    // @Override
    // public ResponseEntity<User> getUserByEmailAndPassword(String email, String password) {
    //     User tempUser = userRepo.findByEmailAndPassword(email, password);
    //     if(tempUser == null){
    //         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    //     }else {
    //         return ResponseEntity.ok(tempUser);
    //     }
    // }

    // @Override
    // public User getUserByEmail(String email) {
    //     // Implementation here
    //     return null;
    // }

    // @Override
    // public void deleteUser(String id) {
    //     User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User Not Found!"));
    //     userRepo.delete(user);
    // }

    // @Override
    // public User getUserById(String id) {
    //     // Implementation here
    //     return null;
    // }

    

    // @Override
    // public List<User> getAllUsers() {
    //     List<User> users = userRepo.findAll();
    //     return users;
    // }

    // @Override
    // public User updateUser(User user) {
    //     User theUser = userRepo.findById(user.getId()).orElseThrow(()-> new ResourceNotFoundException("User Not Found!!"));
    //     // Update th user
    //     theUser.setName(user.getName());
    //     theUser.setEmail(user.getEmail());
    //     theUser.setPassword(user.getPassword());
    //     userRepo.save(theUser);
    //     return theUser;
    // }

   
}
