package com.example.data2.entities.services;

import com.example.data2.entities.APIResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ServiceCategoryData extends APIResponse {

    @SerializedName("data")
    @Expose
    private ArrayList<RemoteServiceCategory> data;


    public ArrayList<RemoteServiceCategory> getData() {
        return data;
    }
}
