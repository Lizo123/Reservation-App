package com.example.data2.entities.services;

import com.example.data2.entities.APIResponse;
import com.example.data2.entities.services.RemoteService;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ServiceData extends APIResponse {

    @SerializedName("data")
    @Expose
    private ArrayList<RemoteService> data;


    public ArrayList<RemoteService> getData() {
        return data;
    }


}
