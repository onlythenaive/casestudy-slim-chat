package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import com.onlythenaive.casestudy.slimchat.service.core.domain.chat.ChatIdWrapper;

public class HistoryIdWrapper {

    private static final String DELIMITER = "$";
    private static final String DELIMITER_REGEX = "\\$";

    public static HistoryIdWrapper empty() {
        return new HistoryIdWrapper();
    }

    public static HistoryIdWrapper fromChatId(String chatId) {
        HistoryIdWrapper wrapper = new HistoryIdWrapper();
        wrapper.chatIdWrapper = ChatIdWrapper.parse(chatId);
        return wrapper;
    }

    public static HistoryIdWrapper parse(String historyId) {
        HistoryIdWrapper wrapper = new HistoryIdWrapper();
        String[] parts = historyId.split(DELIMITER_REGEX);
        wrapper.chatIdWrapper = ChatIdWrapper.parse(parts[0]);
        wrapper.ownerId = parts[1];
        return wrapper;
    }

    private ChatIdWrapper chatIdWrapper;
    private String ownerId;

    public ChatIdWrapper getChatIdWrapper() {
        return this.chatIdWrapper;
    }

    public HistoryIdWrapper chatIdWrapper(ChatIdWrapper chatIdWrapper) {
        this.chatIdWrapper = chatIdWrapper;
        return this;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public HistoryIdWrapper ownerId(String ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public String toHistoryId() {
        return this.chatIdWrapper.toChatId() + DELIMITER + ownerId;
    }
}
