package com.ooogurooo.photoshare.infrastructure.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class CloudinaryClient {
    private Cloudinary cloudinary = new Cloudinary();

    public Map upload(InputStream inputStream, String publicId) throws IOException {
        Transformation transformation = new Transformation().background("black").quality(80);
        Map options = ObjectUtils.asMap(
                "public_id", publicId,
                "transformation", transformation
        );
        
        return cloudinary.uploader().upload(bytesFrom(inputStream), options);
    }

    public String findImageUrls() {
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
