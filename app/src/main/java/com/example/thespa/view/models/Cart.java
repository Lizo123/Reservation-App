package com.example.thespa.view.models;

public class Cart {
    String image;
    String time;
    String amount;

    public Cart( String time, String amount) {

        this.time = time;
        this.amount = amount;
    }


    public String getImage() {
        return image;
    }

    public String getTime() {
        return time;
    }

    public String getAmount() {
        return amount;
    }
}
