package com.ooogurooo.photoshare.service;

import com.ooogurooo.photoshare.model.image.ImageRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {
    ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String list() {
        return imageRepository.list();
    }

    public void post(InputStream image, String imageId) throws IOException {
        imageRepository.save(image, imageId);
    }
}
