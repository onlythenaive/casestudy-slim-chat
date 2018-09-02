package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.access.AccessLevel;

/**
 * Proposal operations facade.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProposalFacadeBean extends GenericComponentBean implements ProposalFacade {

    @Autowired(required = false)
    private Collection<ProposalActionAware> proposalActionHandlers;

    @Autowired
    private ProposalAccessor proposalAccessor;

    @Autowired
    private ProposalPersister proposalPersister;

    @Autowired
    private ProposalProjector proposalProjector;

    @Autowired
    private ProposalProvider proposalProvider;

    @Autowired
    private ProposalRepository proposalRepository;

    @Override
    public Proposal create(ProposalInvoice invoice) {
        ProposalEntity entity = ProposalEntity.builder()
                .text(invoice.getText())
                .initiatorId(principalId())
                .acceptorId(invoice.getAcceptorId())
                .build();
        entity = this.proposalPersister.insert(entity);
        Proposal proposal = project(entity);
        handleAction(this.proposalActionHandlers, handler -> handler.onProposalCreated(proposal));
        return proposal;
    }

    @Override
    public Collection<Proposal> findIncoming() {
        return this.proposalProvider.findIncoming();
    }

    @Override
    public Collection<Proposal> findOutgoing() {
        return this.proposalProvider.findOutgoing();
    }

    @Override
    public void accept(String id) {
        ProposalEntity entity = manageableProposalEntity(id);
        if (!entity.getAcceptorId().equals(principalId())) {
            throw insufficientPrivileges();
        }
        this.proposalRepository.deleteById(id);
        Proposal proposal = project(entity);
        handleAction(this.proposalActionHandlers, handler -> handler.onProposalAccepted(proposal));
    }

    @Override
    public void cancel(String id) {
        ProposalEntity entity = manageableProposalEntity(id);
        if (!entity.getInitiatorId().equals(principalId())) {
            throw insufficientPrivileges();
        }
        this.proposalRepository.deleteById(id);
        Proposal proposal = project(entity);
        handleAction(this.proposalActionHandlers, handler -> handler.onProposalCancelled(proposal));
    }

    @Override
    public void reject(String id) {
        ProposalEntity entity = manageableProposalEntity(id);
        if (!entity.getAcceptorId().equals(principalId())) {
            throw insufficientPrivileges();
        }
        this.proposalRepository.deleteById(id);
        Proposal proposal = project(entity);
        handleAction(this.proposalActionHandlers, handler -> handler.onProposalRejected(proposal));
    }

    private Proposal project(ProposalEntity entity) {
        return this.proposalProjector.project(entity);
    }

    private ProposalEntity manageableProposalEntity(String id) {
        ProposalEntity entity = this.proposalRepository.findById(id).orElseThrow(() -> notFoundById("proposal", id));
        this.proposalAccessor.ensureAccess(entity, AccessLevel.MANAGE);
        return entity;
    }
}
