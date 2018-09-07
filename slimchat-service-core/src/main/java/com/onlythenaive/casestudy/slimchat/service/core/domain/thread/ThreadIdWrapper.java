package com.onlythenaive.casestudy.slimchat.service.core.domain.thread;

/**
 * Helper utility for message thread ID.
 *
 * @author Ilia Gubarev
 */
public final class ThreadIdWrapper {

    private static final String DELIMITER = "+";
    private static final String DELIMITER_REGEX = "\\+";

    /**
     * Creates a new thread ID wrapper from specified group ID.
     *
     * @param groupId the ID of a group.
     * @return a new instance of the wrapper.
     */
    public static ThreadIdWrapper ofGroupId(String groupId) {
        ThreadIdWrapper threadIdWrapper = new ThreadIdWrapper();
        threadIdWrapper.groupId = groupId;
        threadIdWrapper.threadId = groupId;
        return threadIdWrapper;
    }

    /**
     * Creates a new thread ID wrapper from specified profile IDs.
     *
     * @param profileId1 the ID of the first profile.
     * @param profileId2 the ID of the second profile.
     * @return a new instance of the wrapper.
     */
    public static ThreadIdWrapper ofProfileIds(String profileId1, String profileId2) {
        ThreadIdWrapper threadIdWrapper = new ThreadIdWrapper();
        threadIdWrapper.profileId1 = profileId1;
        threadIdWrapper.profileId2 = profileId2;
        String leftParticipantId;
        String rightParticipantId;
        if (profileId1.compareTo(profileId2) < 0) {
            leftParticipantId = profileId1;
            rightParticipantId = profileId2;
        } else {
            leftParticipantId = profileId2;
            rightParticipantId = profileId1;
        }
        threadIdWrapper.threadId = leftParticipantId + DELIMITER + rightParticipantId;
        return threadIdWrapper;
    }

    /**
     * Creates a new thread ID wrapper from specified thread ID.
     *
     * @param threadId the ID of a thread.
     * @return a new instance of the wrapper.
     */
    public static ThreadIdWrapper ofThreadId(String threadId) {
        ThreadIdWrapper threadIdWrapper = new ThreadIdWrapper();
        threadIdWrapper.threadId = threadId;
        if (threadId.contains(DELIMITER)) {
            String[] profileIds = threadId.split(DELIMITER_REGEX);
            threadIdWrapper.profileId1 = profileIds[0];
            threadIdWrapper.profileId2 = profileIds[1];
        } else {
            threadIdWrapper.groupId = threadId;
        }
        return threadIdWrapper;
    }

    private String groupId;
    private String profileId1;
    private String profileId2;
    private String threadId;

    private ThreadIdWrapper() {

    }

    /**
     * Checks if this wrapper contains an ID of a group.
     *
     * @return true if the wrapper contains an ID of group.
     */
    public boolean containsGroupId() {
        return this.groupId != null;
    }

    /**
     * Gets the ID of a group.
     *
     * @return the ID of a group.
     */
    public String getGroupId() {
        return this.groupId;
    }

    /**
     * Gets the ID of the first profile.
     *
     * @return the ID of the first profile.
     */
    public String getProfileId1() {
        return this.profileId1;
    }

    /**
     * Gets the ID of the second profile.
     *
     * @return the ID of the second profile.
     */
    public String getProfileId2() {
        return this.profileId2;
    }

    /**
     * Gets the ID of a thread.
     *
     * @return the ID of a thread.
     */
    public String getThreadId() {
        return this.threadId;
    }

    /**
     * Gets the ID of the companion.
     *
     * @param participantId the ID of the participant (another profile).
     * @return the ID of the companion.
     */
    public String getCompanionId(String participantId) {
        return this.profileId1.equals(participantId) ? this.profileId2 : this.profileId1;
    }
}
