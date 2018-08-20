package com.onlythenaive.casestudy.slimchat.service.core.domain.chat;

public interface ChatService {

    Chat createChat(Chat chat);

    Chat getChatById(String id);

    Chat updateChat(String id, Chat chat);
}
