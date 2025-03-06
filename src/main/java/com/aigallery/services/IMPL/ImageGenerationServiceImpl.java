package com.aigallery.services.IMPL;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.aigallery.entities.User;
import com.aigallery.services.ImageGenerationService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImageGenerationServiceImpl implements ImageGenerationService{

    @Autowired
    private WebClient webClient;
    
    @Autowired
    private Cloudinary cloudinary;
    
    @Value("${pollinations.api.url}")
    private String pollinationsApiUrl;

    @Override
    public String generateAndStoreImage(String prompt, String model, Integer width, Integer height, Long seed, User user) 
            throws IOException {
        // Build Pollinations AI URL
        String apiUrl = pollinationsApiUrl + prompt + 
                "?model=" + model + 
                "&width=" + width + 
                "&height=" + height + 
                "&seed=" + (seed != null ? seed : System.currentTimeMillis()) +
                "&nologo=true";

        // Get image from Pollinations AI
        byte[] imageBytes = webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();

        // Upload to Cloudinary
        Map uploadResult = cloudinary.uploader().upload(imageBytes,
                ObjectUtils.asMap("folder", "user_" + user.getId()));
                
        return (String) uploadResult.get("url");
    }

}
