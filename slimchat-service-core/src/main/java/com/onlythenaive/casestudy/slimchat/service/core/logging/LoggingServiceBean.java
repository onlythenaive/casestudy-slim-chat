package com.onlythenaive.casestudy.slimchat.service.core.logging;

import java.util.Objects;

import org.springframework.stereotype.Service;

/**
 * Event logging service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class LoggingServiceBean implements LoggingService {

    @Override
    public void debug(String messageTemplate, Objects data) {
        log(messageTemplate, data);
    }

    @Override
    public void error(Throwable throwable) {
        // TODO: replace with a normal exception logging strategy
        throwable.printStackTrace();
    }

    @Override
    public void info(String messageTemplate, Objects data) {
        log(messageTemplate, data);
    }

    @Override
    public void warn(String messageTemplate, Objects data) {
        log(messageTemplate, data);
    }

    private void log(String messageTemplate, Objects data) {
        // TODO: replace with a normal event logging strategy
        System.out.format(messageTemplate, data);
    }
}
