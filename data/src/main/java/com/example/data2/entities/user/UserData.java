package com.example.data2.entities.user;

import com.example.data2.entities.APIResponse;
import com.example.data2.entities.RemoteUser;
import com.example.domain.entities.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData extends APIResponse {

    @SerializedName("data")
    @Expose
    private RemoteUser data;

    public RemoteUser getData() {
        return data;
    }

}
