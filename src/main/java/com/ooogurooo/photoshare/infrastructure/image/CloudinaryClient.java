package com.ooogurooo.photoshare.infrastructure.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.ooogurooo.photoshare.model.image.ImageRepository;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Repository
public class CloudinaryClient implements ImageRepository {
    private Cloudinary cloudinary = new Cloudinary();

    @Override
    public Map save(InputStream inputStream, String publicId) throws IOException {
        Transformation transformation = new Transformation().background("black").quality(80);
        Map options = ObjectUtils.asMap(
                "public_id", publicId,
                "transformation", transformation
        );
        return cloudinary.uploader().upload(bytesFrom(inputStream), options);
    }

    @Override
    public String list() {
        return cloudinary.url().publicId("wedding/ee19c600-2bed-4b1e-8d40-e07b44db8216.jpg").generate();
    }

    private byte[] bytesFrom(InputStream inputStream) throws IOException {
        int c;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while ((c = inputStream.read()) != -1) {
            byteArrayOutputStream.write(c);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
