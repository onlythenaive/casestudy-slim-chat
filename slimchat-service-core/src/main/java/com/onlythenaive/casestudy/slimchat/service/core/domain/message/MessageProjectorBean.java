package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainComponentBean;

@Service
public class MessageProjectorBean extends DomainComponentBean implements MessageProjector {

    @Autowired
    private GroupProvider groupProvider;

    @Autowired
    private ProfileProvider profileProvider;

    @Override
    public Message intoMessage(MessageEntity entity) {
        return Message.builder()
                .id(entity.getId())
                .text(entity.getText())
                .author(getAuthor(entity.getAuthorId()))
                .personal(entity.isPersonal())
                .recipient(findRecipient(entity.getRecipientId()).orElse(null))
                .group(findGroup(entity.getGroupId()).orElse(null))
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private Profile getAuthor(String profileId) {
        return this.profileProvider.getById(profileId);
    }

    private Optional<Profile> findRecipient(String profileId) {
        return this.profileProvider.findPreviewById(profileId);
    }

    private Optional<Group> findGroup(String groupId) {
        return this.groupProvider.findPreviewById(groupId);
    }
}
