package com.example.data2.entities.services;

import com.example.data2.entities.APIResponse;
import com.example.data2.entities.services.RemoteService;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServiceDetailsData extends APIResponse {
    @SerializedName("data")
    @Expose
    private RemoteService data;


    public RemoteService getData() {
        return data;
    }

}
