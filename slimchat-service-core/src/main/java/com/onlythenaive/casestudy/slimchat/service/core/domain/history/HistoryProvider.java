package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;

/**
 * Chat history provider.
 *
 * @author Ilia Gubarev
 */
public interface HistoryProvider {

    /**
     * Provides an existing chat history by its ID.
     *
     * @param id the ID of an existing history.
     * @return the requested history.
     */
    History getById(String id);

    /**
     * Provides a collections of existing chat histories previews by their owner ID.
     *
     * @param ownerId the ID of teh owner.
     * @return a collection of requested histories.
     */
    Collection<History> findPreviewsByOwnerId(String ownerId);
}
