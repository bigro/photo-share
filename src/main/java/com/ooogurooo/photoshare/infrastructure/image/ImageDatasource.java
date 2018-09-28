package com.ooogurooo.photoshare.infrastructure.image;

import com.ooogurooo.photoshare.model.image.ImageRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;

@Repository
public class ImageDatasource implements ImageRepository {
    
    private JdbcTemplate jdbcTemplate;

    public ImageDatasource(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(InputStream inputStream, String publicId) throws IOException {
        CloudinaryClient cloudinaryClient = new CloudinaryClient();
        cloudinaryClient.upload(inputStream, publicId);

        jdbcTemplate.update("INSERT INTO image VALUES (?)", publicId);
    }

    @Override
    public String list() {
        CloudinaryClient cloudinaryClient = new CloudinaryClient();
        return cloudinaryClient.findImageUrls();
    }
}
