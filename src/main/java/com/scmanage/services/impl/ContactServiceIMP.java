package com.scmanage.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.scmanage.entities.Contact;
import com.scmanage.entities.User;
import com.scmanage.helpers.ResourceNotFoundException;
import com.scmanage.repository.ContactRepo;
import com.scmanage.services.Contactservice;

@Service
public class ContactServiceIMP implements Contactservice {

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact SavaContacts(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);

    }

    @Override
    public Contact update(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public List<Contact> getContactsList() {
        return contactRepo.findAll();
    }

    @Override
    public Contact GetContactById(String id) {
        return contactRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found for: " + id));
    }

    @Override
    public void DeleteContact(String id) {
        var contact = contactRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found for: " + id));
        contactRepo.delete(contact);
    }

    @Override
    public List<Contact> getByUserId(String userid) {
        return contactRepo.findByUserId(userid);
    }


    @Override
    public Page<Contact> getByUser(User user, int pageOffset, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageOffset, pageSize, sort);
        return contactRepo.findByUser(user, pageable);
    }
    
    @Override
    public Page<Contact> searchByName(String name,int pageNo, int pageSize, String sortingField, String sortDirection, User user) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortingField).ascending() : Sort.by(sortingField).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return contactRepo.findByUserAndNameContaining(user,name, pageable);
    }
    
    @Override
    public Page<Contact> searchByEmail(String email,int pageNo, int pageSize, String sortingField, String sortDirection,User user) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortingField).ascending() : Sort.by(sortingField).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    
        return contactRepo.findByUserAndEmailContaining(user,email, pageable);
    }
    
    @Override
    public Page<Contact> searchByPhone(String phone,int pageNo, int pageSize, String sortingField, String sortDirection,User user) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortingField).ascending() : Sort.by(sortingField).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
    
        return contactRepo.findByUserAndPhoneNumberContaining(user,phone, pageable);
    }
    
    // @Override
    // public Page<Contact> searchByFavorite(boolean isfavorite, int pageNo, int pageSize, String sortingField,
    // String sortDirection,User user) {
    //             Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortingField).ascending() : Sort.by(sortingField).descending();
    //             Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
            
    //             return contactRepo.findByFavoriteContaining(isfavorite, pageable);

    // }


}
