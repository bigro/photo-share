package com.ooogurooo.photoshare.model.image;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface ImageRepository {
    void save(InputStream inputStream, String publicId) throws IOException;
    
    String list();
}   
