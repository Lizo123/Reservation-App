package com.example.data2.entities.beauticians;

import com.example.data2.entities.APIResponse;
import com.example.data2.entities.offers.RemoteOffer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BeauticiansData extends APIResponse {


    @SerializedName("data")
    @Expose
    private ArrayList<RemoteBeautician> data;

    public ArrayList<RemoteBeautician> getData() {
        return data;
    }
}
