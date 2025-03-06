package com.aigallery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aigallery.entities.GeneratedImage;
import com.aigallery.entities.User;

@Repository
public interface GeneratedImageRepository extends JpaRepository<GeneratedImage, Long> {
    List<GeneratedImage> findByUser(User user);
    List<GeneratedImage> findByUserOrderByIdDesc(User user);
}
