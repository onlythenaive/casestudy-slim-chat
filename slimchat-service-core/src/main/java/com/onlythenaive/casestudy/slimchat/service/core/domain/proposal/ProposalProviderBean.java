package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Connection proposal provider implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProposalProviderBean extends GenericComponentBean implements ProposalProvider {

    @Autowired
    private ProposalAccessor proposalAccessor;

    @Autowired
    private ProposalProjector proposalProjector;

    @Autowired
    private ProposalRepository proposalRepository;

    @Override
    public Collection<Proposal> findIncoming() {
        return this.proposalRepository.findByAcceptorId(principalId()).stream()
                .map(this::project)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Proposal> findOutgoing() {
        return this.proposalRepository.findByInitiatorId(principalId()).stream()
                .map(this::project)
                .collect(Collectors.toList());
    }

    private Proposal project(ProposalEntity entity) {
        return this.proposalProjector.project(entity);
    }
}
