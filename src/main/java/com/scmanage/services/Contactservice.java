package com.scmanage.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.scmanage.entities.Contact;
import com.scmanage.entities.User;

public interface Contactservice {
    Contact SavaContacts(Contact contact);
    Contact update(Contact contact);
    List<Contact> getContactsList();
    Contact GetContactById(String id);
    void DeleteContact(String id);
    Page<Contact> searchByName(String nameKeyword,int pageNo, int pageSize, String sortingField, String sortDirection,User user);
    Page<Contact> searchByEmail(String emailKeyword,int pageNo, int pageSize, String sortingField, String sortDirection,User user);
    Page<Contact> searchByPhone(String phoneKeyword,int pageNo, int pageSize, String sortingField, String sortDirection,User user);
    // Page<Contact> searchByFavorite(boolean isFavorite,int pageNo, int pageSize, String sortingField, String sortDirection);
    List<Contact> getByUserId(String userid);
    Page<Contact> getByUser(User user,int pageNo, int pageSize, String sortingField, String sortDirection);

}
