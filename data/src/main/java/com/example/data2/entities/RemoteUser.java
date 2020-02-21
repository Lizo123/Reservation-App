package com.example.data2.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoteUser {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("registered_date")
    @Expose
    private String registeredDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("active")
    @Expose
    private String active;

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public String getStatus() {
        return status;
    }

    public String getActive() {
        return active;
    }


}
