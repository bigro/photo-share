package com.ooogurooo.photoshare.infrastructure;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class CloudinaryClient {
    private Cloudinary cloudinary = new Cloudinary();
    
    public Map upload(InputStream inputStream) throws IOException {
        Transformation transformation = new Transformation().colorSpace("black").quality(80);
        Map options = ObjectUtils.asMap("transformation", transformation);
        return cloudinary.uploader().upload(bytesFrom(inputStream), options);
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
