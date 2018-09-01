package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

public class MessageChatDescriptorBuilder {

    public static MessageChatDescriptorBuilder of(MessageEntity entity) {
        return new MessageChatDescriptorBuilder()
                .groupId(entity.getGroupId())
                .profileId1(entity.getAuthorId())
                .profileId2(entity.getRecipientId());
    }

    public static MessageChatDescriptorBuilder of(MessageSearchInvoice invoice) {
        return new MessageChatDescriptorBuilder()
                .groupId(invoice.getGroupId())
                .profileId1(invoice.getProfileId1())
                .profileId2(invoice.getProfileId2());
    }

    private String groupId;
    private String profileId1;
    private String profileId2;

    public MessageChatDescriptorBuilder groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    public MessageChatDescriptorBuilder profileId1(String profileId1) {
        this.profileId1 = profileId1;
        return this;
    }

    public MessageChatDescriptorBuilder profileId2(String profileId2) {
        this.profileId2 = profileId2;
        return this;
    }

    public String build() {
        if (this.groupId != null) {
            return "group-chat-" + this.groupId;
        }
        String leftParticipantId;
        String rightParticipantId;
        if (this.profileId1.compareTo(this.profileId2) < 0) {
            leftParticipantId = profileId1;
            rightParticipantId = profileId2;
        } else {
            leftParticipantId = profileId1;
            rightParticipantId = profileId2;
        }
        return "personal-chat-" + leftParticipantId + "-and-" + rightParticipantId;
    }
}
