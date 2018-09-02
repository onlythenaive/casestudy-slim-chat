package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfilePreviewProvider;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Connection proposal projector.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProposalProjectorBean extends GenericComponentBean implements ProposalProjector {

    @Autowired
    private ProfilePreviewProvider profileProvider;

    @Override
    public Proposal project(ProposalEntity entity) {
        return Proposal.builder()
                .id(entity.getId())
                .text(entity.getText())
                .initiator(profilePreview(entity.getInitiatorId()))
                .acceptor(profilePreview(entity.getAcceptorId()))
                .acceptedByPrincipal(entity.getAcceptorId().equals(principalId()))
                .initiatedByPrincipal(entity.getInitiatorId().equals(principalId()))
                .createdAt(entity.getCreatedAt())
                .lastModifiedAt(entity.getLastModifiedAt())
                .build();
    }

    private Profile profilePreview(String profileId) {
        return this.profileProvider.getById(profileId);
    }
}
