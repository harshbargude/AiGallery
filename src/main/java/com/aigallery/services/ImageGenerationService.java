package com.aigallery.services;

import java.io.IOException;

import com.aigallery.entities.User;

public interface ImageGenerationService {
    String generateAndStoreImage(String prompt, String model, Integer width, Integer height, Long seed, User user) throws IOException;
}
