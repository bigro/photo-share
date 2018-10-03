package com.ooogurooo.photoshare.infrastructure.image;

import com.ooogurooo.photoshare.model.image.Image;
import com.ooogurooo.photoshare.model.image.ImageIdentifier;
import com.ooogurooo.photoshare.model.image.ImageRepository;
import com.ooogurooo.photoshare.model.image.Images;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ImageDatasource implements ImageRepository {

    private JdbcTemplate jdbcTemplate;
    private CloudinaryClient cloudinaryClient = new CloudinaryClient();

    public ImageDatasource(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String save(InputStream inputStream, String publicId) throws IOException {
        String url = cloudinaryClient.upload(inputStream, publicId);

        jdbcTemplate.update("INSERT INTO image VALUES (?)", publicId);
        
        return url;
    }

    @Override
    public Images list() {
        List<ImageIdentifier> imageIdentifiers = jdbcTemplate.query("SELECT public_id FROM image", new ImageIdentifierMapper());

        ArrayList<Image> images = new ArrayList<>();
        for (ImageIdentifier imageIdentifier : imageIdentifiers) {
            String imageUrl = cloudinaryClient.findImageUrls(imageIdentifier.value());
            images.add(new Image(imageUrl));
        }
        
        return new Images(images);
    }
    
    
    private static final class ImageIdentifierMapper implements RowMapper<ImageIdentifier> {
        public ImageIdentifier mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new ImageIdentifier(rs.getString("public_id"));
        }
    }

}
