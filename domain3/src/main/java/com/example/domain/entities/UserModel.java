package com.example.domain.entities;

public class UserModel {
    private String name;
    private String number;


    public UserModel(String name, String number) {
        this.name = name;
        this.number = number;

    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }


}
