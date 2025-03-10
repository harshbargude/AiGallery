package com.aigallery.web.controllers;

import java.io.IOException;

import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aigallery.entities.GeneratedImage;
import com.aigallery.entities.User;
import com.aigallery.repository.GeneratedImageRepository;
import com.aigallery.repository.UserRepo;
import com.aigallery.services.ImageGenerationService;


@Controller
@RequestMapping("/aigallery/user")
public class UserImageGenerationController {
    private UserRepo userRepository;
    private GeneratedImageRepository imageRepository;
    private ImageGenerationService imageGenerationService;

    public UserImageGenerationController(UserRepo userRepository, GeneratedImageRepository imageRepository, ImageGenerationService imageGenerationService) {
        this.imageGenerationService = imageGenerationService;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    @GetMapping
    public String welcome(Model model) {
        User user = userRepository.findByEmail(
            SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        model.addAttribute("images", imageRepository.findByUserOrderByIdDesc(user));
        model.addAttribute("imageRequest", new GeneratedImage());
        return "user/profile";
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
        User user = userRepository.findByEmail(
            SecurityContextHolder.getContext().getAuthentication().getName());
        List<GeneratedImage> images = imageRepository.findByUser(user);   
        int totalImages = images.size(); 
        model.addAttribute("numberOfImages", totalImages);
        return "user/dashboard";
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        User user = userRepository.findByEmail(
            SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/generate")
    public String getGenerateImage(Model model) {
        User user = userRepository.findByEmail(
            SecurityContextHolder.getContext().getAuthentication().getName());
        // model.addAttribute("user", user);
        model.addAttribute("images", imageRepository.findByUserOrderByIdDesc(user));
        model.addAttribute("imageRequest", new GeneratedImage());
        return "user/generate_image";
    }

    @GetMapping("/generated-images")
    public String getGeneratedImages(Model model) {
        User user = userRepository.findByEmail(
            SecurityContextHolder.getContext().getAuthentication().getName());
        // model.addAttribute("user", user);
        model.addAttribute("images", imageRepository.findByUserOrderByIdDesc(user));
        model.addAttribute("imageRequest", new GeneratedImage());
        return "user/generated-images";
    }




    @PostMapping("/generate")
    public String generateImage(@ModelAttribute GeneratedImage imageRequest) throws IOException {
        User user = userRepository.findByEmail(
            SecurityContextHolder.getContext().getAuthentication().getName());
            
        String imageUrl = imageGenerationService.generateAndStoreImage(
            imageRequest.getPrompt(),
            imageRequest.getModel() != null ? imageRequest.getModel() : "flux",
            imageRequest.getWidth() != null ? imageRequest.getWidth() : 512,
            imageRequest.getHeight() != null ? imageRequest.getHeight() : 512,
            imageRequest.getSeed(),
            user
        );

        GeneratedImage generatedImage = new GeneratedImage();
        generatedImage.setPrompt(imageRequest.getPrompt());
        generatedImage.setCloudinaryUrl(imageUrl);
        generatedImage.setModel(imageRequest.getModel());
        generatedImage.setWidth(imageRequest.getWidth());
        generatedImage.setHeight(imageRequest.getHeight());
        generatedImage.setSeed(imageRequest.getSeed());
        generatedImage.setUser(user);
        
        imageRepository.save(generatedImage);
        return "redirect:/aigallery/user/generate";
    }
    
}
