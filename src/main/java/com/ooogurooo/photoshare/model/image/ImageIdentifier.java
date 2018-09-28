package com.ooogurooo.photoshare.model.image;

/**
 * 投稿画像の識別子
 */
public class ImageIdentifier {
    String value;

    public ImageIdentifier(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
