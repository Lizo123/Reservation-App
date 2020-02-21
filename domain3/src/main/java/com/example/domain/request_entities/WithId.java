package com.example.domain.request_entities;

public class WithId {

    String lang;

    String id;

    public WithId(String lang, String id) {
        this.lang = lang;
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public String getId() {
        return id;
    }
}
