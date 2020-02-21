package com.example.domain.entities;

public class Response {

    public Object success;
    public Object error;


    public Response(Object success, Object error) {
        this.success = success;
        this.error = error;
    }
}
