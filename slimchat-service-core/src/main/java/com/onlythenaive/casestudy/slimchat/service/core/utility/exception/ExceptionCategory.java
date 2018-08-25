package com.onlythenaive.casestudy.slimchat.service.core.utility.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Exception category.
 *
 * @author Ilia Gubarev
 */
public enum ExceptionCategory {

    /**
     * Exceptions caused by input data of format validation.
     */
    VALIDATION("x.validation", HttpStatus.BAD_REQUEST),

    /**
     * Security violations.
     */
    SECURITY("x.security", HttpStatus.FORBIDDEN),

    /**
     * Exceptions occurred because of missing resources required for an operation.
     */
    RESOURCE("x.resource", HttpStatus.NOT_FOUND),

    /**
     * Exceptions caused by business logic violations during operation processing.
     */
    CONFLICT("x.conflict", HttpStatus.CONFLICT),

    /**
     * System connectivity or integrity problems.
     */
    CONNECTIVITY("x.connectivity", INTERNAL_SERVER_ERROR),

    /**
     * Exceptions of unknown origin, mostly unexpected.
     */
    UNPREDICTED("x.unpredicted", INTERNAL_SERVER_ERROR);

    private final String defaultTextcode;
    private final HttpStatus defaultHttpStatus;

    ExceptionCategory(String defaultTextcode, HttpStatus defaultHttpStatus) {
        this.defaultTextcode = defaultTextcode;
        this.defaultHttpStatus = defaultHttpStatus;
    }

    /**
     * Gets a default exception textcode for this exception category.
     *
     * @return default exception textcode.
     */
    public String getDefaultTextcode() {
        return this.defaultTextcode;
    }

    /**
     * Gets a default HTTP status code for this exception category.
     *
     * @return default HTTP status code
     */
    public HttpStatus getDefaultHttpStatus() {
        return this.defaultHttpStatus;
    }
}
