package com.ooogurooo.photoshare.model.image;

import com.ooogurooo.photoshare.model.criteria.Limit;

import java.io.IOException;
import java.io.InputStream;

public interface ImageRepository {
    String save(InputStream inputStream, String publicId) throws IOException;
    
    Images list(Limit limit);
}   
