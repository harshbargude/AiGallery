package com.aigallery.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aigallery.helpers.Message;
import com.aigallery.helpers.MessageType;
import com.aigallery.services.UserService;
import com.aigallery.web.dto.forms.UserForm;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService theUserService){
        this.userService = theUserService;
    }

    @GetMapping
    public String showRegistrationForm(Model model){
        model.addAttribute("userForm",new UserForm());
        return "register";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult theBindingResult, HttpSession httpSession) {

        // here you can process the form data
        if(theBindingResult.hasErrors()){
            System.out.println(theBindingResult.toString());
            theBindingResult.toString();
            return "/register";
        }
        userService.saveUser(userForm);

        // add message
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        httpSession.setAttribute("message", message);
//        System.out.println("UserForm data: " + userForm.toString());
        return "redirect:/register";
    }

}
