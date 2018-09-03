package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;

/**
 * Chat message provider.
 *
 * @author Ilia Gubarev
 */
public interface MessageProvider {

    /**
     * Provides a collection of messages by specified chat's ID.
     *
     * @param chatId the ID of a chat.
     * @return the resulting collection.
     */
    Collection<Message> getAllByChatId(String chatId);

    /**
     * Provides a collection of the latest messages for each principal's chats.
     *
     * @return the resulting collection.
     */
    Collection<Message> getAllLatestInChat();
}
