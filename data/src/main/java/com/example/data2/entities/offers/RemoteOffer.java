package com.example.data2.entities.offers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoteOffer {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("branch_id")
    @Expose
    private String branchId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("discount_type")
    @Expose
    private String discountType;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("expiry_date")
    @Expose
    private String expiryDate;

    public String getId() {
        return id;
    }

    public String getBranchId() {
        return branchId;
    }

    public String getName() {
        return name;
    }

    public String getDiscountType() {
        return discountType;
    }

    public String getDiscount() {
        return discount;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}
