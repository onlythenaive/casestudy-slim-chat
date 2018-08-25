package com.onlythenaive.casestudy.slimchat.service.core.utility.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Operation exception.
 *
 * @author Ilia Gubarev
 */
public class OperationException extends RuntimeException {

    /**
     * Operation exception builder.
     */
    public static class Builder {

        private Throwable cause;
        private String comment;
        private ExceptionCategory category;
        private Object data;
        private Map<String, Object> dataAttributes;
        private String textcode;

        /**
         * Gets the initial exception cause.
         *
         * @param cause the initial exception cause.
         * @return the builder instance.
         */
        public Builder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        /**
         * Sets a comment of the exception.
         *
         * @param comment a comment of the exception.
         * @return the builder instance.
         */
        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        /**
         * Sets the category of the exception.
         *
         * @param category the category of the exception.
         * @return the builder instance.
         */
        public Builder category(ExceptionCategory category) {
            this.category = category;
            return this;
        }

        /**
         * Sets a data attachment.
         *
         * @param data a data attachment.
         * @return the builder instance.
         */
        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        /**
         * Sets a data attribute.
         *
         * @param name the name of an attribute.
         * @param value the value to be set.
         * @return the builder instance.
         */
        public Builder dataAttribute(String name, Object value) {
            if (this.dataAttributes == null) {
                this.dataAttributes = new HashMap<>();
            }
            this.dataAttributes.put(name, value);
            return this;
        }

        /**
         * Sets the textcode of an exception.
         *
         * @param textcode the textcode of an exception.
         * @return the builder instance.
         */
        public Builder textcode(String textcode) {
            this.textcode = textcode;
            return this;
        }

        /**
         * Creates a new operation exception.
         *
         * @return operation exception to be thrown.
         */
        public OperationException build() {
            return new OperationException(this);
        }
    }

    /**
     * Creates a new opeartion exception builder.
     *
     * @return
     */
    public static Builder builder() {
        return new Builder();
    }

    private final ExceptionCategory category;
    private final Object data;
    private final String textcode;

    private OperationException(Builder builder) {
        super(builder.comment, builder.cause);
        this.category = builder.category != null ? builder.category : ExceptionCategory.UNPREDICTED;
        this.data = builder.data != null ? builder.data : builder.dataAttributes;
        this.textcode = builder.textcode != null ? builder.textcode : this.category.getDefaultTextcode();
    }

    /**
     * Gets the category of this exception.
     *
     * @return the category of the exception.
     */
    public ExceptionCategory getCategory() {
        return this.category;
    }

    /**
     * Gets the attached data if any.
     *
     * @return the attached data.
     */
    public Object getData() {
        return this.data;
    }

    /**
     * Gets the textcode of the exception.
     *
     * @return the textcode of this exception.
     */
    public String getTextcode() {
        return this.textcode;
    }
}
