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

    /**
     * Retrieves an existing message by its ID.
     *
     * @param id the ID of the message.
     * @return the requested message.
     */
    Message get(String id);

    /**
     * Retrieves a search result.
     *
     * @param invoice a search invoice.
     * @return search result.
     */
    MessageSearchResult getSearchResult(MessageSearchInvoice invoice);
}
