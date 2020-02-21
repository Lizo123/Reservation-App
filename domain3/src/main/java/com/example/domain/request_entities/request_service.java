package com.example.domain.request_entities;

public class request_service {

    String lang;
    int serviceId;

    public request_service(String lang, int serviceId) {
        this.lang = lang;
        this.serviceId = serviceId;
    }

    public String getLang() {
        return lang;
    }

    public int getServiceId() {
        return serviceId;
    }
}
