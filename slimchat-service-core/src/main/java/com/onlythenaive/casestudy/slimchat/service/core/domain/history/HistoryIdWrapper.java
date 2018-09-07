package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import com.onlythenaive.casestudy.slimchat.service.core.domain.thread.ThreadIdWrapper;

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
     * @param threadId the thread's ID wrapper.
     * @return a new wrapper.
     */
    public static HistoryIdWrapper fromThreadId(String threadId) {
        HistoryIdWrapper wrapper = new HistoryIdWrapper();
        wrapper.threadIdWrapper = ThreadIdWrapper.ofThreadId(threadId);
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
        wrapper.threadIdWrapper = ThreadIdWrapper.ofThreadId(parts[0]);
        wrapper.ownerId = parts[1];
        return wrapper;
    }

    private ThreadIdWrapper threadIdWrapper;
    private String ownerId;

    /**
     * Gets the thread's ID wrapper.
     *
     * @return the thread's ID wrapper.
     */
    public ThreadIdWrapper getThreadIdWrapper() {
        return this.threadIdWrapper;
    }

    /**
     * Sets a new thread's ID wrapper.
     *
     * @param threadIdWrapper a thread's ID wrapper.
     * @return this wrapper.
     */
    public HistoryIdWrapper threadIdWrapper(ThreadIdWrapper threadIdWrapper) {
        this.threadIdWrapper = threadIdWrapper;
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
        return this.threadIdWrapper.getThreadId() + DELIMITER + ownerId;
    }
}
