package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileEntity;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProjector;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileRepository;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;

@Service
public class MessageProjectorBean extends GenericDomainComponentBean implements MessageProjector {

    @Autowired
    private ProfileProjector profileProjector;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Message intoMessage(MessageEntity entity) {
        return Message.builder()
                .id(entity.getId())
                .author(authorProfile(entity.getAuthorId()))
                .chatId(entity.getChatId())
                .createdAt(entity.getCreatedAt())
                .text(entity.getText())
                .build();
    }

    private Profile authorProfile(String authorId) {
        ProfileEntity profileEntity = this.profileRepository.getById(authorId);
        return this.profileProjector.intoProfile(profileEntity);
    }
}
