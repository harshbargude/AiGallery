package com.aigallery.entities;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class GeneratedImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prompt;
    private String cloudinaryUrl;
    private String model;
    private Integer width;
    private Integer height;
    private Long seed;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
