package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import java.util.Collection;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.EntityRepository;

/**
 * Connection proposal repository.
 *
 * @author Ilia Gubarev
 */
public interface ProposalRepository extends EntityRepository<ProposalEntity> {

    /**
     * Finds all existing proposals by their acceptors's ID.
     *
     * @param acceptorId the ID of acceptor.
     * @return the resulting collection of proposals.
     */
    Collection<ProposalEntity> findByAcceptorId(String acceptorId);

    /**
     * Finds all existing proposals by their initiator's ID.
     *
     * @param initiatorId the ID of initiator.
     * @return the resulting collection of proposals.
     */
    Collection<ProposalEntity> findByInitiatorId(String initiatorId);

    @Override
    default String getEntityName() {
        return ProposalEntity.NAME;
    }
}
