package com.ooogurooo.photoshare.model.image;

import java.io.IOException;
import java.io.InputStream;

public interface ImageRepository {
    String save(InputStream inputStream, String publicId) throws IOException;
    
    Images list();
}   
