package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import java.util.Collection;

/**
 * Connection proposal provider.
 *
 * @author Ilia Gubarev
 */
public interface ProposalProvider {

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
