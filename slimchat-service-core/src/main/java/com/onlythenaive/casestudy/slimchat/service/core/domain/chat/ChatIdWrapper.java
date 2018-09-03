package com.onlythenaive.casestudy.slimchat.service.core.domain.chat;

/**
 * Helper utility for chat ID.
 *
 * @author Ilia Gubarev
 */
public class ChatIdWrapper {

    private static final String DELIMITER = "+";
    private static final String DELIMITER_REGEX = "\\+";

    /**
     * Creates a new wrapper.
     *
     * @return a new wrapper.
     */
    public static ChatIdWrapper empty() {
        return new ChatIdWrapper();
    }

    /**
     * Creates a new wrapper from an existing chat ID.
     *
     * @param chatId a chat ID to be used.
     * @return a new wrapper.
     */
    public static ChatIdWrapper parse(String chatId) {
        ChatIdWrapper wrapper = new ChatIdWrapper();
        if (chatId.contains(DELIMITER)) {
            String[] profileIds = chatId.split(DELIMITER_REGEX);
            wrapper.profileId1(profileIds[0]);
            wrapper.profileId2(profileIds[1]);
        } else {
            wrapper.groupId(chatId);
        }
        return wrapper;
    }

    private String groupId;
    private String profileId1;
    private String profileId2;

    /**
     * Gets the ID of a group.
     *
     * @return the ID of a group.
     */
    public String getGroupId() {
        return this.groupId;
    }

    /**
     * Sets a new ID of a group.
     *
     * @param groupId the ID of a group.
     * @return this wrapper.
     */
    public ChatIdWrapper groupId(String groupId) {
        this.groupId = groupId;
        return this;
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
     * Sets a new ID of the first profile.
     *
     * @param profileId1 the ID of the first profile.
     * @return this wrapper.
     */
    public ChatIdWrapper profileId1(String profileId1) {
        this.profileId1 = profileId1;
        return this;
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
     * Sets a new ID of the second profile.
     *
     * @param profileId2 the ID of the second profile.
     * @return this wrapper.
     */
    public ChatIdWrapper profileId2(String profileId2) {
        this.profileId2 = profileId2;
        return this;
    }

    /**
     * Gets the ID of a chat.
     *
     * @return the ID of a chat.
     */
    public String toChatId() {
        if (this.groupId != null) {
            return this.groupId;
        }
        String leftParticipantId;
        String rightParticipantId;
        if (this.profileId1.compareTo(this.profileId2) < 0) {
            leftParticipantId = profileId1;
            rightParticipantId = profileId2;
        } else {
            leftParticipantId = profileId2;
            rightParticipantId = profileId1;
        }
        return leftParticipantId + DELIMITER + rightParticipantId;
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
