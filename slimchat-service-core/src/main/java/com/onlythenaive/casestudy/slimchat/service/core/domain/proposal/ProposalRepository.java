package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import java.util.Collection;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainRepository;

/**
 * Connection proposal repository.
 *
 * @author Ilia Gubarev
 */
public interface ProposalRepository extends DomainRepository<ProposalEntity> {

    /**
     * Checks if a proposal exists between specified users.
     *
     * @param initiatorId the ID of an initiator.
     * @param acceptorId the ID of an acceptor.
     * @return true if a proposal exists.
     */
    boolean existsByInitiatorIdAndAcceptorId(String initiatorId, String acceptorId);

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
}
