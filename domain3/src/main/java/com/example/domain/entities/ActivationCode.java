package com.example.domain.entities;

public class ActivationCode {

    private String number;
    private String code;
    private String password;

    public ActivationCode( String number,String code,String password) {

        this.number = number;
        this.code = code;
        this.password = password;
    }



    public String getNumber() {
        return number;
    }

    public String getCode()
    {return code;}
    public String getPassword()
    {
        return password;
    }
}
