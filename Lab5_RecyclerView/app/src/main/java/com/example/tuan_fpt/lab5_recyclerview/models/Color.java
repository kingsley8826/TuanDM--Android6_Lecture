package com.example.tuan_fpt.lab5_recyclerview.models;

/**
 * Created by Tuan-FPT on 23/12/2016.
 */

public class Color {
    private String name;
    private String key;

    public Color(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return name;
    }
}
