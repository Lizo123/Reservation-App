package com.example.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceCategory implements Parcelable {

    private String id;

    private String name;

    private String description;

    private String image;

    public ServiceCategory(String id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    protected ServiceCategory(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        image = in.readString();
    }

    public static final Creator<ServiceCategory> CREATOR = new Creator<ServiceCategory>() {
        @Override
        public ServiceCategory createFromParcel(Parcel in) {
            return new ServiceCategory(in);
        }

        @Override
        public ServiceCategory[] newArray(int size) {
            return new ServiceCategory[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(image);
    }
}
