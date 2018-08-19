package com.onlythenaive.casestudy.slimchat.service.core.generic;

import java.time.Instant;
import java.util.UUID;

/**
 * Generic component implementation.
 *
 * @author Ilia Gubarev
 */
public abstract class GenericComponentBean {

    /**
     * Provides current date and time.
     *
     * @return current date and time.
     */
    protected Instant now() {
        return Instant.now();
    }

    /**
     * Provides a new unique UUID.
     *
     * @return a new UUID.
     */
    protected String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * Creates a runtime exception that wraps a specified exception.
     *
     * @param exception an exception that needs to be wrapped.
     * @return runtime exception.
     */
    protected RuntimeException wrappedException(Exception exception) {
        return new RuntimeException(exception);
    }
}
