package com.scmanage.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scmanage.entities.Contact;
import com.scmanage.services.Contactservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private Contactservice contactService;
    // get contact bu user
    @GetMapping("/contacts/{contactId}")
    public Contact getContact(@PathVariable String contactId) {
            
        return contactService.GetContactById(contactId);

    }
    @GetMapping("/contacts/delete/{contactId}")
    public String deleteContact(@PathVariable String contactId) {
        contactService.DeleteContact(contactId);
        return "redirect:/user/contacts";
    }
}
