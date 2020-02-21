package com.example.domain.entities;

public class Product extends ProductCategory {


    public String productCode;

    public String price;



    public Product(String id, String name, String description, String image, String productCode, String price) {
        super(id, name, description, image);
        this.productCode = productCode;
        this.price = price;
    }
}
