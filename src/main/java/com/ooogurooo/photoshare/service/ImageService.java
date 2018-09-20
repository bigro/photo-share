package com.ooogurooo.photoshare.service;

import com.ooogurooo.photoshare.model.image.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String list() {
        return imageRepository.list();
    }
}
