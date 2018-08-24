package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import java.util.Collection;

/**
 * Connection proposal provider.
 *
 * @author Ilia Gubarev
 */
public interface ProposalProvider {

    /**
     * Retrieves an existing proposal by its ID.
     *
     * @param id the ID of an existing proposal.
     * @return the requested proposal.
     */
    Proposal getById(String id);

    /**
     * Finds all incoming proposals.
     *
     * @return the resulting collection of proposals.
     */
    Collection<Proposal> findIncoming();

    /**
     * Finds all outgoing proposals.
     *
     * @return the resulting collection of proposals.
     */
    Collection<Proposal> findOutgoing();
}
