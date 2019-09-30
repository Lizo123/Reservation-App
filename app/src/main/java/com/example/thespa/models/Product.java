package com.example.thespa.models;

public class Product {
    String image;
    String title;
    String description;


    public Product(String title, String description) {
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
