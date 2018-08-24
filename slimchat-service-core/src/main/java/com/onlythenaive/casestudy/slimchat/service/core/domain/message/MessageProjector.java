package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

/**
 * Chat message projector.
 *
 * @author Ilia Gubarev
 */
public interface MessageProjector {

    /**
     * Creates a projection of an existing chat message.
     *
     * @param entity an existing chat message entity.
     * @return the resulting projection.
     */
    Message project(MessageEntity entity);
}
