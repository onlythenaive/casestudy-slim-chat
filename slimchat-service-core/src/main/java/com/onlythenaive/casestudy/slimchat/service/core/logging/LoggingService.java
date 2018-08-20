package com.onlythenaive.casestudy.slimchat.service.core.logging;

import java.util.Objects;

/**
 * Event logging service.
 *
 * @author Ilia Gubarev
 */
public interface LoggingService {

    /**
     * Logs a debug-level event.
     *
     * @param messageTemplate log message template.
     * @param data additive data objects to be included into the message.
     */
    void debug(String messageTemplate, Objects data);

    /**
     * Logs an occurred error.
     *
     * @param throwable an occurred exception.
     */
    void error(Throwable throwable);

    /**
     * Logs a normal event.
     *
     * @param messageTemplate log message template.
     * @param data additive data objects to be included into the message.
     */
    void info(String messageTemplate, Objects data);

    /**
     * Logs a warning-level event.
     *
     * @param messageTemplate log message template.
     * @param data additive data objects to be included into the message.
     */
    void warn(String messageTemplate, Objects data);
}
