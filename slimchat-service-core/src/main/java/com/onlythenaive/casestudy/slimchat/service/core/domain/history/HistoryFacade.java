package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;

/**
 * Chat history operation facade.
 *
 * @author Ilia Gubarev
 */
public interface HistoryFacade {

    /**
     * Retrieves an existing chat history.
     *
     * @param id the ID of an existing chat history.
     * @return the requested chat history
     */
    History get(String id);

    /**
     * Retrieves a collection of chat histories for the current principal.
     *
     * @return existing chat histories.
     */
    Collection<History> find();

    /**
     * Removes an existing chat history.
     *
     * @param id the ID of an existing chat history.
     */
    void remove(String id);
}
