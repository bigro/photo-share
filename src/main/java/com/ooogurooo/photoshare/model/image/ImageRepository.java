package com.ooogurooo.photoshare.model.image;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public interface ImageRepository {
    Map save(InputStream inputStream, String publicId) throws IOException;
    
    String list();
}   
