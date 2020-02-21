package com.example.data2.entities.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoteProduct {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("product_code")
    @Expose
    private String productCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("image")
    @Expose
    private String image;

    public String getId() {
        return id;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
