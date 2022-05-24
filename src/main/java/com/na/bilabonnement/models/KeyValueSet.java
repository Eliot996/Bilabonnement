package com.na.bilabonnement.models;

public class KeyValueSet {
    int value;
    String name;

    public KeyValueSet(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
