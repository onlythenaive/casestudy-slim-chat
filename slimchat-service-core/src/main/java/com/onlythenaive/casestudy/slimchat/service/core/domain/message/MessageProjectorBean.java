package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProvider;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Chat message projector implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class MessageProjectorBean extends GenericComponentBean implements MessageProjector {

    @Autowired
    private GroupProvider groupProvider;

    @Autowired
    private ProfileProvider profileProvider;

    @Override
    public Message project(MessageEntity entity) {
        return Message.builder()
                .id(entity.getId())
                .text(entity.getText())
                .author(profile(entity.getAuthorId()))
                .recipient(profile(entity.getRecipientId()))
                .group(group(entity.getGroupId()))
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private Group group(String groupId) {
        return this.groupProvider.findPreviewById(groupId).orElse(null);
    }

    private Profile profile(String profileId) {
        return this.profileProvider.findPreviewById(profileId).orElse(null);
    }
}
