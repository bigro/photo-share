package com.ooogurooo.photoshare.model.criteria;

public class Limit {
    int value;

    public Limit(int value) {
        this.value = value;
    }

    public boolean none() {
        return value == 0;
    }

    public int value() {
        return value;
    }
}
