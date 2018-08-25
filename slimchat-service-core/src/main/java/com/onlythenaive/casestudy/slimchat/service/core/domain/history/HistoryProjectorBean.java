package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProvider;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Chat history projector implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class HistoryProjectorBean extends GenericComponentBean implements HistoryProjector {

    @Autowired
    private GroupProvider groupProvider;

    @Autowired
    private MessageProvider messageProvider;

    @Autowired
    private ProfileProvider profileProvider;

    @Override
    public History project(HistoryEntity entity) {
        return History.builder()
                .id(entity.getId())
                .owner(profile(entity.getOwnerId()))
                .referencedUser(profile(entity.getReferencedUserId()))
                .referencedGroup(group(entity.getReferencedGroupId()))
                .messages(messages(entity.getMessageIds()))
                .build();
    }

    @Override
    public History projectPreview(HistoryEntity entity) {
        return History.builder()
                .id(entity.getId())
                .owner(profile(entity.getOwnerId()))
                .referencedUser(profile(entity.getReferencedUserId()))
                .referencedGroup(group(entity.getReferencedGroupId()))
                .build();
    }

    private Group group(String groupId) {
        return this.groupProvider.findPreviewById(groupId).orElse(null);
    }

    private Profile profile(String profileId) {
        return this.profileProvider.findPreviewById(profileId).orElse(null);
    }

    private Collection<Message> messages(Collection<String> messageIds) {
        return this.messageProvider.findByIds(messageIds);
    }
}
