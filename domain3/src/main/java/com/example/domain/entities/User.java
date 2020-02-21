package com.example.domain.entities;

public class User extends UserModel {

    private String email;
    private String password;
    private String isActive;
    private String id;

    public User(String name, String number, String email, String isActive,String id) {
        super(name, number);
        this.email = email;
        this.isActive = isActive;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getIsActive() {
        return isActive;
    }

    public String getId() {
        return id;
    }
}
