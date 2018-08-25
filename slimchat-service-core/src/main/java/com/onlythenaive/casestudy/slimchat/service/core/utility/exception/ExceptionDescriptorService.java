package com.onlythenaive.casestudy.slimchat.service.core.utility.exception;

/**
 * Exception descriptor service.
 *
 * @author Ilia Gubarev
 */
public interface ExceptionDescriptorService {

    /**
     * Creates a default descriptor for an unknown exception.
     *
     * @return default exception descriptor.
     */
    ExceptionDescriptor defaultDescriptor();

    /**
     * Extracts a descriptor from specified exception.
     *
     * @param exception an exception to be processed.
     * @return exception descriptor.
     */
    ExceptionDescriptor extract(OperationException exception);

    /**
     * Extracts a descriptor from specified exception.
     *
     * @param exception an exception to be processed.
     * @return exception descriptor.
     */
    ExceptionDescriptor extract(Exception exception);
}
