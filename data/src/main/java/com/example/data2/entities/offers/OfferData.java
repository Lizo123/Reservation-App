package com.example.data2.entities.offers;

import com.example.data2.entities.APIResponse;
import com.example.data2.entities.offers.RemoteOffer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OfferData extends APIResponse {

    @SerializedName("data")
    @Expose
    private ArrayList<RemoteOffer> data;


    public ArrayList<RemoteOffer> getData() {
        return data;
    }
}
