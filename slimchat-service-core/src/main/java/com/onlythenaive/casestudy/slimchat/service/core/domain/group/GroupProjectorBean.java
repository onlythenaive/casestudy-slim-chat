package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;

@Service
public class GroupProjectorBean extends GenericDomainComponentBean implements GroupProjector {

    @Autowired
    private ProfileProvider profileProvider;

    @Override
    public Group intoGroup(GroupEntity entity) {
        return Group.builder()
                .id(entity.getId())
                .caption(entity.getCaption())
                .participants(profiles(entity.getParticipantIds()))
                .moderators(profiles(entity.getModeratorIds()))
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private Collection<Profile> profiles(Collection<String> profileIds) {
        return this.profileProvider.findPreviews(profileIds);
    }
}
