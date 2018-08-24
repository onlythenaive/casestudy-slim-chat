package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import java.util.Collection;

/**
 * Proposal operations facade.
 *
 * @author Ilia Gubarev
 */
public interface ProposalFacade {

    /**
     * Creates a new connection proposal.
     *
     * @param invoice an invoice of a proposal.
     * @return the created proposal.
     */
    Proposal create(ProposalInvoice invoice);

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

    /**
     * Accepts an existing proposal by its ID.
     *
     * @param id the id of an existing proposal.
     */
    void accept(String id);

    /**
     * Cancels an existing proposal by its ID.
     *
     * @param id the id of an existing proposal.
     */
    void cancel(String id);

    /**
     * Rejects an existing proposal by its ID.
     *
     * @param id the id of an existing proposal.
     */
    void reject(String id);
}
