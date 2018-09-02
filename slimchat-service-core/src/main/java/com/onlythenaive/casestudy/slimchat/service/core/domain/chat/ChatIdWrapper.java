package com.onlythenaive.casestudy.slimchat.service.core.domain.chat;

public class ChatIdWrapper {

    private static final String DELIMITER = "+";
    private static final String DELIMITER_REGEX = "\\+";

    public static ChatIdWrapper empty() {
        return new ChatIdWrapper();
    }

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

    public String getGroupId() {
        return this.groupId;
    }

    public ChatIdWrapper groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getProfileId1() {
        return this.profileId1;
    }

    public ChatIdWrapper profileId1(String profileId1) {
        this.profileId1 = profileId1;
        return this;
    }

    public String getProfileId2() {
        return this.profileId2;
    }

    public ChatIdWrapper profileId2(String profileId2) {
        this.profileId2 = profileId2;
        return this;
    }

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

    public String getCompanionId(String participantId) {
        return this.profileId1.equals(participantId) ? this.profileId2 : this.profileId1;
    }
}
