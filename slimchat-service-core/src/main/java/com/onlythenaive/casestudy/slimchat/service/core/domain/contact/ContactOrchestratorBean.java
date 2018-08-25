package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileAccessor;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileEntity;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfilePersister;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProjector;
import com.onlythenaive.casestudy.slimchat.service.core.domain.proposal.Proposal;
import com.onlythenaive.casestudy.slimchat.service.core.domain.proposal.ProposalActionAware;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainComponentBean;

/**
 * Contact orchestrator implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ContactOrchestratorBean extends DomainComponentBean implements ProposalActionAware {

    @Autowired(required = false)
    private Collection<ContactActionAware> contactHandlers;

    @Autowired
    private ProfileAccessor profileAccessor;

    @Autowired
    private ProfilePersister profilePersister;

    @Autowired
    private ProfileProjector profileProjector;

    @Override
    public void onProposalCreated(Proposal proposal) {

    }

    @Override
    public void onProposalAccepted(Proposal proposal) {
        String acceptorId = proposal.getAcceptor().getId();
        String initiatorId = proposal.getAcceptor().getId();
        ProfileEntity acceptorEntity = this.profileAccessor.accessById(AccessLevel.PREVIEW, acceptorId);
        ProfileEntity initiatorEntity = this.profileAccessor.accessById(AccessLevel.PREVIEW, initiatorId);
        acceptorEntity.getConnectedUserIds().add(initiatorId);
        initiatorEntity.getConnectedUserIds().add(acceptorId);
        this.profilePersister.update(acceptorEntity);
        this.profilePersister.update(initiatorEntity);
        Profile acceptor = this.profileProjector.projectPreview(acceptorEntity);
        Profile initiator = this.profileProjector.projectPreview(initiatorEntity);
        this.contactHandlers.forEach(handler -> handler.onContactCreated(acceptor, initiator));
    }

    @Override
    public void onProposalCancelled(Proposal proposal) {

    }

    @Override
    public void onProposalRejected(Proposal proposal) {

    }

    @PostConstruct
    void init() {
        logger().debug("Registered handlers: {}", this.contactHandlers.size());
    }
}
