package com.scmanage.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uplodeImage(MultipartFile contactImage, String fileName);
    String getUrlFromPublicId (String publicId);

}
