package com.onlythenaive.casestudy.slimchat.service.core.utility.component;

import java.time.Instant;
import java.util.UUID;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generic implementation of a component.
 *
 * @author Ilia Gubarev
 */
public abstract class GenericComponentBean {

    private Logger logger;

    /**
     * Provides a current logger.
     *
     * @return current logger.
     */
    protected Logger logger() {
        return this.logger;
    }

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

    @PostConstruct
    private void init() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }
}
