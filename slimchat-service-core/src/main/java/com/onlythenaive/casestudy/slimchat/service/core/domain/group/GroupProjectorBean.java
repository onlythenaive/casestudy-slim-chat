package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfilePreviewProvider;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Group chat projector service.
 *
 * @author Ilia Gubarev
 */
@Service
public class GroupProjectorBean extends GenericComponentBean implements GroupProjector {

    @Autowired
    private ProfilePreviewProvider profileProvider;

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
        return this.profileProvider.getById(profileIds);
    }
}
