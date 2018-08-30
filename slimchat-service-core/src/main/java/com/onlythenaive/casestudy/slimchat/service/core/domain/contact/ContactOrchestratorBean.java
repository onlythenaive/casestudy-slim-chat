package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileAccessor;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileEntity;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfilePersister;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProjector;
import com.onlythenaive.casestudy.slimchat.service.core.domain.proposal.Proposal;
import com.onlythenaive.casestudy.slimchat.service.core.domain.proposal.ProposalActionAware;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.AccessLevel;

/**
 * Contact orchestrator implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ContactOrchestratorBean extends GenericComponentBean implements ProposalActionAware {

    @Autowired(required = false)
    private Collection<ContactActionAware> contactActionHandlers;

    @Autowired
    private ProfileAccessor profileAccessor;

    @Autowired
    private ProfilePersister profilePersister;

    @Autowired
    private ProfileProjector profileProjector;

    @Override
    public void onProposalAccepted(Proposal proposal) {
        String acceptorId = proposal.getAcceptor().getId();
        String initiatorId = proposal.getInitiator().getId();
        ProfileEntity acceptorEntity = this.profileAccessor.accessById(AccessLevel.BYPASS, acceptorId);
        ProfileEntity initiatorEntity = this.profileAccessor.accessById(AccessLevel.BYPASS, initiatorId);
        acceptorEntity.getConnectedProfileIds().add(initiatorId);
        initiatorEntity.getConnectedProfileIds().add(acceptorId);
        this.profilePersister.update(acceptorEntity);
        this.profilePersister.update(initiatorEntity);
        Profile acceptor = this.profileProjector.projectPreview(acceptorEntity);
        Profile initiator = this.profileProjector.projectPreview(initiatorEntity);
        handleAction(this.contactActionHandlers, handler -> handler.onContactCreated(acceptor, initiator));
    }
}
