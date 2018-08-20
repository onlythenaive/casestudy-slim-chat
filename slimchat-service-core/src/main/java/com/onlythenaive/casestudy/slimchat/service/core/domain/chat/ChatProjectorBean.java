package com.onlythenaive.casestudy.slimchat.service.core.domain.chat;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageProjector;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageRepository;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProjector;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileRepository;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;

@Service
public class ChatProjectorBean extends GenericDomainComponentBean implements ChatProjector {

    @Autowired
    private MessageProjector messageProjector;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ProfileProjector profileProjector;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Chat intoChat(ChatEntity entity) {
        return Chat.builder()
                .id(entity.getId())
                .caption(entity.getCaption())
                .personal(entity.getPersonal())
                .admins(profiles(entity.getAdminIds()))
                .participants(profiles(entity.getParticipantIds()))
                .latestMessages(latestMessages(entity.getId()))
                .build();
    }

    private Collection<Message> latestMessages(String chatId) {
        return this.messageRepository.findByChatId(chatId)
                .stream()
                .map(this.messageProjector::intoMessage)
                .collect(Collectors.toList());
    }

    private Collection<Profile> profiles(Collection<String> profileIds) {
        return this.profileRepository.findAllById(profileIds)
                .stream()
                .map(this.profileProjector::intoProfile)
                .collect(Collectors.toList());
    }
}
