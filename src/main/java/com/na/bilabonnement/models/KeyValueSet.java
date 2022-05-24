package com.na.bilabonnement.models;

public class KeyValueSet {
    int key;
    String value;

    public KeyValueSet(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
