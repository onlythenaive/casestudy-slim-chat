package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import com.onlythenaive.casestudy.slimchat.service.core.domain.thread.ThreadIdWrapper;

/**
 * Utility helper for chat history ID.
 *
 * @author Ilia Gubarev
 */
public final class HistoryIdWrapper {

    private static final String DELIMITER = "$";
    private static final String DELIMITER_REGEX = "\\$";

    /**
     * Creates a new history ID wrapper from specified history ID.
     *
     * @param historyId the ID of a history to be wrapped.
     * @return a new wrapper.
     */
    public static HistoryIdWrapper ofHistoryId(String historyId) {
        HistoryIdWrapper wrapper = new HistoryIdWrapper();
        wrapper.historyId = historyId;
        String[] parts = historyId.split(DELIMITER_REGEX);
        wrapper.ownerId = parts[1];
        wrapper.threadId = parts[0];
        return wrapper;
    }

    /**
     * Creates a new history ID wrapper from specified thread's and owner's IDs.
     *
     * @param threadId the ID of a thread.
     * @param ownerId the ID of the owner.
     * @return a new wrapper.
     */
    public static HistoryIdWrapper ofThreadIdAndOwnerId(String threadId, String ownerId) {
        HistoryIdWrapper wrapper = new HistoryIdWrapper();
        wrapper.historyId = threadId + DELIMITER + ownerId;
        wrapper.ownerId = ownerId;
        wrapper.threadId = threadId;
        return wrapper;
    }

    private String historyId;
    private String ownerId;
    private String threadId;

    private HistoryIdWrapper() {

    }

    /**
     * Gets the ID of a history.
     *
     * @return the ID of a history.
     */
    public String getHistoryId() {
        return this.historyId;
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
     * Gets the ID of a thread.
     *
     * @return the ID of a thread.
     */
    public String getThreadId() {
        return this.threadId;
    }
}
