package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainComponentBean;

/**
 * Group chat projector service.
 *
 * @author Ilia Gubarev
 */
@Service
public class GroupProjectorBean extends DomainComponentBean implements GroupProjector {

    @Autowired
    private ProfileProvider profileProvider;

    @Override
    public Group project(GroupEntity entity) {
        return Group.builder()
                .id(entity.getId())
                .caption(entity.getCaption())
                .participants(profilePreviews(entity.getParticipantIds()))
                .moderators(profilePreviews(entity.getModeratorIds()))
                .principalIsParticipant(principalIsParticipant(entity))
                .principalIsModerator(principalIsModerator(entity))
                .lastModifiedAt(entity.getLastModifiedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    @Override
    public Group projectPreview(GroupEntity entity) {
        return Group.builder()
                .id(entity.getId())
                .caption(entity.getCaption())
                .principalIsParticipant(principalIsParticipant(entity))
                .principalIsModerator(principalIsModerator(entity))
                .build();
    }

    private boolean principalIsParticipant(GroupEntity entity) {
        return entity.getParticipantIds().contains(principalId());
    }

    private boolean principalIsModerator(GroupEntity entity) {
        return entity.getParticipantIds().contains(principalId());
    }

    private Collection<Profile> profilePreviews(Collection<String> profileIds) {
        return this.profileProvider.findPreviews(profileIds);
    }
}
