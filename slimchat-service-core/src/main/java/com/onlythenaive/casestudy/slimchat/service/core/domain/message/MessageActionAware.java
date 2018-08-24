package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

/**
 * Component aware of message-related actions.
 *
 * @author Ilia Gubarev
 */
public interface MessageActionAware {

    /**
     * Handles message creation.
     *
     * @param message a new created message.
     */
    void onMessageCreated(Message message);
}
