package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

/**
 * Chat history projector.
 *
 * @author Ilia Gubarev
 */
public interface HistoryProjector {

    /**
     * Creates a chat history projection from its entity.
     *
     * @param entity a chat history entity to be projected.
     * @return the created projection.
     */
    History project(HistoryEntity entity);

    /**
     * Creates a chat history projection preview from its entity.
     *
     * @param entity a chat history entity to be projected.
     * @return the created projection.
     */
    History projectPreview(HistoryEntity entity);
}
