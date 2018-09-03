package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;

/**
 * Message repository extension.
 *
 * @author Ilia Gubarev
 */
public interface MessageRepositoryExtension {

    /**
     * Gets a collection of messages which are the latest in the chats for specified observer.
     *
     * @param observerId the ID of an observer.
     * @return the resulting collection of messages.
     */
    Collection<MessageEntity> getLatestInChat(String observerId);
}
