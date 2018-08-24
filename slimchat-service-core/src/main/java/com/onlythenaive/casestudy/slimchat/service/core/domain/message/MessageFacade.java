package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

/**
 * Chat message operations facade.
 *
 * @author Ilia Gubarev
 */
public interface MessageFacade {

    /**
     * Creates a new chat message.
     *
     * @param message a chat message invoice.
     * @return the created chat message.
     */
    Message create(MessageInvoice message);
}
