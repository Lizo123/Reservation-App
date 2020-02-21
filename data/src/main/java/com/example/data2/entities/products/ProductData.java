package com.example.data2.entities.products;

import com.example.data2.entities.APIResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductData extends APIResponse {
    @SerializedName("data")
    @Expose
    private ArrayList<RemoteProduct> data;


    public ArrayList<RemoteProduct> getData() {
        return data;
    }
}
