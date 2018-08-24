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
    void onProposalCreated(Proposal proposal);

    /**
     * Handles acceptation of a proposal.
     *
     * @param proposal an affected proposal.
     */
    void onProposalAccepted(Proposal proposal);

    /**
     * Handles cancellation of a proposal.
     *
     * @param proposal an affected proposal.
     */
    void onProposalCancelled(Proposal proposal);

    /**
     * Handles rejection of a proposal.
     *
     * @param proposal an affected proposal.
     */
    void onProposalRejected(Proposal proposal);
}
