package com.scmanage.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scmanage.entities.User;
import com.scmanage.helpers.Helper;
import com.scmanage.services.UserService;

@ControllerAdvice
public class RootController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ModelAttribute // @ModelAttribute -> used so this following modal can be used by all the
                    // methods in this class
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        // String name = principal.getName();

        if(authentication == null){
            return;
        }
        System.out.println("Adding logdin user to the modal");
        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("User Logged in: {}", username);

        // database se data ko fetch: getUser from from database
        User user = userService.getUserByEmail(username);
        System.out.println(user);
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        // System.out.println(user.getPassword());

        model.addAttribute("loggedInUser", user); // key-value pair NOTE- this key is used in thym leaf to access model
                                                  // data

    }
}
