package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageProjector;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageRepository;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;

@Service
public class HistoryProjectorBean extends GenericDomainComponentBean implements HistoryProjector {

    @Autowired
    private GroupProvider groupProvider;

    @Autowired
    private MessageProjector messageProjector;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ProfileProvider profileProvider;

    @Override
    public History intoHistory(HistoryEntity entity) {
        return History.builder()
                .id(entity.getId())
                .owner(projectOwner(entity.getOwnerId()))
                .personal(entity.isPersonal())
                .referencedUser(projectReferencedUser(entity.getReferencedUserId()))
                .referencedGroup(projectReferencedGroup(entity.getReferencedGroupId()))
                .messages(projectMessages(entity.getMessageIds()))
                .build();
    }

    private Group projectReferencedGroup(String groupId) {
        return this.groupProvider.findGroup(groupId).orElse(null);
    }

    private Profile projectOwner(String ownerId) {
        return this.profileProvider.getById(ownerId);
    }

    private Profile projectReferencedUser(String userId) {
        return this.profileProvider.findPreviewById(userId).orElse(null);
    }

    private Collection<Message> projectMessages(Collection<String> messageIds) {
        return this.messageRepository.findAllById(messageIds).stream()
                .map(this.messageProjector::intoMessage)
                .collect(Collectors.toList());
    }
}
