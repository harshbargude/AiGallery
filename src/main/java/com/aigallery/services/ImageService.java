package com.aigallery.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aigallery.entities.User;

public interface ImageService {

    List<User> getAllUsers();
    String uplodeImage(MultipartFile contactImage, String fileName);
    String getUrlFromPublicId (String publicId);

}
