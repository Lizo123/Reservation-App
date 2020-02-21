package com.example.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Offer implements Parcelable {


    private String id;

    private String branchId;

    private String name;

    private String discountType;

    private String discount;

    private String expiryDate;

    public Offer(String id, String branchId, String name, String discountType, String discount, String expiryDate) {
        this.id = id;
        this.branchId = branchId;
        this.name = name;
        this.discountType = discountType;
        this.discount = discount;
        this.expiryDate = expiryDate;
    }

    protected Offer(Parcel in) {
        id = in.readString();
        branchId = in.readString();
        name = in.readString();
        discountType = in.readString();
        discount = in.readString();
        expiryDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(branchId);
        dest.writeString(name);
        dest.writeString(discountType);
        dest.writeString(discount);
        dest.writeString(expiryDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Offer> CREATOR = new Creator<Offer>() {
        @Override
        public Offer createFromParcel(Parcel in) {
            return new Offer(in);
        }

        @Override
        public Offer[] newArray(int size) {
            return new Offer[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getName() {
        return name;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getDiscountType() {
        return discountType;
    }

    public String getDiscount() {
        return discount;
    }
}
