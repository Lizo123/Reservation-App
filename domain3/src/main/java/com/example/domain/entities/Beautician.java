package com.example.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Beautician implements Parcelable {

    private String id;

    private String branchId;

    private String name;

    private String jobTitle;

    private String description;

    private String image;

    private String phone;

    private String mobile;

    private String email;

    public Beautician(String id, String branchId, String name, String jobTitle, String description, String image, String phone, String mobile, String email) {
        this.id = id;
        this.branchId = branchId;
        this.name = name;
        this.jobTitle = jobTitle;
        this.description = description;
        this.image = image;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
    }

    protected Beautician(Parcel in) {
        id = in.readString();
        branchId = in.readString();
        name = in.readString();
        jobTitle = in.readString();
        description = in.readString();
        image = in.readString();
        phone = in.readString();
        mobile = in.readString();
        email = in.readString();
    }

    public static final Creator<Beautician> CREATOR = new Creator<Beautician>() {
        @Override
        public Beautician createFromParcel(Parcel in) {
            return new Beautician(in);
        }

        @Override
        public Beautician[] newArray(int size) {
            return new Beautician[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(branchId);
        dest.writeString(name);
        dest.writeString(jobTitle);
        dest.writeString(description);
        dest.writeString(image);
        dest.writeString(phone);
        dest.writeString(mobile);
        dest.writeString(email);
    }
}
