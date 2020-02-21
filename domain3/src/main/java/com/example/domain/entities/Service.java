package com.example.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Service implements Parcelable {


    private String id;

    private String categoryId;

    private String name;

    private String description;

    private String image;

    private String price;

    private String duration;

    private String isFavorite;

    public Service(String id, String categoryId, String name, String description, String image, String price, String duration, String isFavorite) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.duration = duration;
        this.isFavorite = isFavorite;
    }

    protected Service(Parcel in) {
        id = in.readString();
        categoryId = in.readString();
        name = in.readString();
        description = in.readString();
        image = in.readString();
        price = in.readString();
        duration = in.readString();
        isFavorite = in.readString();
    }

    public static final Creator<Service> CREATOR = new Creator<Service>() {
        @Override
        public Service createFromParcel(Parcel in) {
            return new Service(in);
        }

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getCategoryId() {
        return categoryId;
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

    public String getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(categoryId);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeString(price);
        dest.writeString(duration);
        dest.writeString(isFavorite);
    }
}
