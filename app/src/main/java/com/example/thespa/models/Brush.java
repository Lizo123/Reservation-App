package com.example.thespa.models;

public class Brush {
    String image;
    String title;
    String description;


    public Brush(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
