package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;

/**
 * Chat message provider.
 *
 * @author Ilia Gubarev
 */
public interface MessageProvider {

    /**
     * Provides a collection of messages by specified thread's ID.
     *
     * @param threadId the ID of a chat.
     * @return the resulting collection.
     */
    Collection<Message> getAllByThreadId(String threadId);

    /**
     * Provides a collection of the latest messages for each principal's threads.
     *
     * @return the resulting collection.
     */
    Collection<Message> getAllLatestInThread();
}
