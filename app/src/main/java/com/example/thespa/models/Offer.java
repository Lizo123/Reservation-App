package com.example.thespa.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Offer implements Parcelable {

    private String offerImage;
    private String offerName;
    private String offerPercentage;

    public Offer( String offerName, String offerPercentage) {
        this.offerImage = offerImage;
        this.offerName = offerName;
        this.offerPercentage = offerPercentage;
    }

    protected Offer(Parcel in) {
        offerImage = in.readString();
        offerName = in.readString();
        offerPercentage = in.readString();
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

    public String getOfferImage() {
        return offerImage;
    }

    public String getOfferName() {
        return offerName;
    }

    public String getOfferPercentage() {
        return offerPercentage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(offerImage);
        dest.writeString(offerName);
        dest.writeString(offerPercentage);
    }
}
