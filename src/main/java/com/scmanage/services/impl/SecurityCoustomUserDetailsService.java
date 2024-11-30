package com.scmanage.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scmanage.repository.UserRepo;

@Service
public class SecurityCoustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // upna user ko lode karana hai
        return userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

}
