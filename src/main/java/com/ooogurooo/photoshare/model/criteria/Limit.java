package com.ooogurooo.photoshare.model.criteria;

public class Limit {
    Integer value;

    public Limit(Integer value) {
        this.value = value;
    }

    public boolean none() {
        return value == null;
    }

    public Integer value() {
        return value;
    }
}
