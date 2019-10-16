package com.example.thespa.models;

import android.graphics.Bitmap;
import android.net.Uri;

import java.net.URI;

public class Contact {

    public String id;
    public String name;
    public String mobileNumber;
    public Bitmap photo;
    public Uri photoUri;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public Uri getPhotoUri() {
        return photoUri;
    }
}
