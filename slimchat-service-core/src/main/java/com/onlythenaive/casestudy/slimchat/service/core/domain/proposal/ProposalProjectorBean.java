package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainComponentBean;

/**
 * Connection proposal projector.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProposalProjectorBean extends DomainComponentBean implements ProposalProjector {

    @Autowired
    private ProfileProvider profileProvider;

    @Override
    public Proposal project(ProposalEntity entity) {
        return Proposal.builder()
                .id(entity.getId())
                .text(entity.getText())
                .initiator(profilePreview(entity.getInitiatorId()))
                .acceptor(profilePreview(entity.getAcceptorId()))
                .createdAt(entity.getCreatedAt())
                .lastModifiedAt(entity.getLastModifiedAt())
                .build();
    }

    private Profile profilePreview(String profileId) {
        return this.profileProvider.findPreviewById(profileId).orElse(null);
    }
}
