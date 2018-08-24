package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;

/**
 * Chat message provider.
 *
 * @author Ilia Gubarev
 */
public interface MessageProvider {

    /**
     * Provides a collection of existing messages by their IDs.
     *
     * @param ids a collection of message IDs.
     * @return the resulting collection of messages.
     */
    Collection<Message> findByIds(Collection<String> ids);
}
