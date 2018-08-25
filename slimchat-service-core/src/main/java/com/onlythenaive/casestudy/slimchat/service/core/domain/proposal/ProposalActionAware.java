package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

/**
 * Component aware of proposal-related actions
 *
 * @author Ilia Gubarev
 */
public interface ProposalActionAware {

    /**
     * Handles creation of a new proposal.
     *
     * @param proposal a new created proposal.
     */
    default void onProposalCreated(Proposal proposal) {

    }

    /**
     * Handles acceptation of a proposal.
     *
     * @param proposal an affected proposal.
     */
    default void onProposalAccepted(Proposal proposal) {

    }

    /**
     * Handles cancellation of a proposal.
     *
     * @param proposal an affected proposal.
     */
    default void onProposalCancelled(Proposal proposal) {

    }

    /**
     * Handles rejection of a proposal.
     *
     * @param proposal an affected proposal.
     */
    default void onProposalRejected(Proposal proposal) {

    }
}
