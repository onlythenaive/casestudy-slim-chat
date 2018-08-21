package com.onlythenaive.casestudy.slimchat.service.core.domain.chat;

public interface ChatFacade {

    Chat createChat(ChatInvoice invoice);

    Chat getChatById(String id);
}
