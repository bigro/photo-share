package com.ooogurooo.photoshare.service;

import com.ooogurooo.photoshare.model.criteria.Limit;
import com.ooogurooo.photoshare.model.image.ImageRepository;
import com.ooogurooo.photoshare.model.image.Images;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {
    ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Images list(Limit limit) {
        return imageRepository.list(limit);
    }

    public String post(InputStream image, String imageId) throws IOException {
        return imageRepository.save(image, imageId);
    }
}
