package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import com.onlythenaive.casestudy.slimchat.service.core.domain.chat.ChatIdWrapper;

/**
 * Chat history ID wrapper.
 *
 * @author Ilia Gubarev
 */
public class HistoryIdWrapper {

    private static final String DELIMITER = "$";
    private static final String DELIMITER_REGEX = "\\$";

    /**
     * Creates a new wrapper.
     *
     * @return a new wrapper.
     */
    public static HistoryIdWrapper empty() {
        return new HistoryIdWrapper();
    }

    /**
     * Creates a new wrapper.
     *
     * @param chatId the chat's ID wrapper.
     * @return a new wrapper.
     */
    public static HistoryIdWrapper fromChatId(String chatId) {
        HistoryIdWrapper wrapper = new HistoryIdWrapper();
        wrapper.chatIdWrapper = ChatIdWrapper.parse(chatId);
        return wrapper;
    }

    /**
     * Creates a new wrapper from specified ID.
     *
     * @param historyId the ID of a history to be used.
     * @return a new wrapper.
     */
    public static HistoryIdWrapper parse(String historyId) {
        HistoryIdWrapper wrapper = new HistoryIdWrapper();
        String[] parts = historyId.split(DELIMITER_REGEX);
        wrapper.chatIdWrapper = ChatIdWrapper.parse(parts[0]);
        wrapper.ownerId = parts[1];
        return wrapper;
    }

    private ChatIdWrapper chatIdWrapper;
    private String ownerId;

    /**
     * Gets the chat's ID wrapper.
     *
     * @return the chat's ID wrapper.
     */
    public ChatIdWrapper getChatIdWrapper() {
        return this.chatIdWrapper;
    }

    /**
     * Sets a new chat's ID wrapper.
     *
     * @param chatIdWrapper a chat's ID wrapper.
     * @return this wrapper.
     */
    public HistoryIdWrapper chatIdWrapper(ChatIdWrapper chatIdWrapper) {
        this.chatIdWrapper = chatIdWrapper;
        return this;
    }

    /**
     * Gets the ID of the owner.
     *
     * @return the ID of the owner.
     */
    public String getOwnerId() {
        return this.ownerId;
    }

    /**
     * Sets a new ID of the owner.
     *
     * @param ownerId a new ID of the owner.
     * @return this wrapper.
     */
    public HistoryIdWrapper ownerId(String ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    /**
     * Gets the ID of a history.
     *
     * @return history's ID.
     */
    public String toHistoryId() {
        return this.chatIdWrapper.toChatId() + DELIMITER + ownerId;
    }
}
