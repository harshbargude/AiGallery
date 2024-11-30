package com.scmanage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    // note 
    // a handler -> a handler to actually show the contact form
    // add contact page/view
    @RequestMapping("/add")
    public String addContactView(){
        return "user/add_contact" ;  //remember rout hum tagda banayyyy
    }
    @RequestMapping("/random")
    public String random(){
        return "user/random" ;  //remember rout hum tagda banayyyy
    }


}
