package com.onlythenaive.casestudy.slimchat.service.core.exception;

/**
 * Exception category.
 *
 * @author Ilia Gubarev
 */
public enum ExceptionCategory {

    /**
     * Exceptions caused by input data of format validation.
     */
    VALIDATION("x.validation"),

    /**
     * Exceptions caused by business logic violations during operation processing.
     */
    LOGIC("x.logic"),

    /**
     * Security violations.
     */
    SECURITY("x.security"),

    /**
     * System connectivity or integrity problems.
     */
    CONNECTIVITY("x.connectivity"),

    /**
     * Exceptions of unknown origin, mostly unexpected.
     */
    UNKNOWN("x.unknown");

    private String defaultTextcode;

    ExceptionCategory(String defaultTextcode) {
        this.defaultTextcode = defaultTextcode;
    }

    /**
     * Gets a default exception textcode.
     *
     * @return default exception textcode.
     */
    public String getDefaultTextcode() {
        return defaultTextcode;
    }
}
