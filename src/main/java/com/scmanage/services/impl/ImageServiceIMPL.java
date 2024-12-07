package com.scmanage.services.impl;

import java.io.IOException;
import java.util.UUID;

// import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scmanage.helpers.AppConstants;
import com.scmanage.services.ImageService;

@Service
public class ImageServiceIMPL implements ImageService{

    private Cloudinary cloudinary;
    


    public ImageServiceIMPL(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }



    @Override
    public String uplodeImage(MultipartFile contactImage, String fileName) {
        // Cloud me image store carneka  code   yaha hai bhi

        try {
            byte[] data = new byte[contactImage.getInputStream().available()];

            contactImage.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap(
                "public_id", fileName
                
            ));


            return this.getUrlFromPublicId(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


        // return karnea hogea image :url

        // Using Cloudinary OR AWS Cloud
        // I AM USING Cloudinary;
    }



    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary
            .url()
            .transformation(
                new Transformation<>()
                .width(AppConstants.CONTACT_IMAGE_WIDTH)
                .height(AppConstants.CONTACT_IMAGE_HEIGHT)
                .crop(AppConstants.CONTACT_IMAGE_CROP)
            )
            .generate(publicId);
    }


    

}
