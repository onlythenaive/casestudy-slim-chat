package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;

/**
 * Chat message provider.
 *
 * @author Ilia Gubarev
 */
public interface MessageProvider {

    Collection<Message> getAllByChatId(String chatId);

    Collection<Message> getAllLatestInChat();
}
