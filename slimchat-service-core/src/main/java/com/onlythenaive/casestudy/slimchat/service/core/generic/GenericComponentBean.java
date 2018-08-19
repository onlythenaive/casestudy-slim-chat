package com.onlythenaive.casestudy.slimchat.service.core.generic;

public abstract class GenericComponentBean {

    public RuntimeException wrappedException(Exception exception) {
        return new RuntimeException(exception);
    }
}
