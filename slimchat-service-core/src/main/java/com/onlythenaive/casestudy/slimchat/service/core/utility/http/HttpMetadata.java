package com.onlythenaive.casestudy.slimchat.service.core.utility.http;

public enum HttpMetadata {

    SECURITY_TOKEN("X-Security-Token");

    private String textcode;

    HttpMetadata(String textcode) {
        this.textcode = textcode;
    }

    public String getTextcode() {
        return this.textcode;
    }
}
