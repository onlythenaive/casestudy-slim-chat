package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;

/**
 * Chat message operations facade.
 *
 * @author Ilia Gubarev
 */
public interface MessageFacade {

    /**
     * Creates a new chat message.
     *
     * @param invoice a chat message invoice.
     * @return the created chat message.
     */
    Message create(MessageInvoice invoice);

    Message get(String id);

    Collection<Message> getLatestFromEachChat();

    Collection<Message> getSearchResult(MessageSearchInvoice invoice);
}
