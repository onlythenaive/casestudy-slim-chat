package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

/**
 * Connection proposal projector.
 *
 * @author Ilia Gubarev
 */
public interface ProposalProjector {

    /**
     * Creates a projection of connection proposal from its entity.
     *
     * @param entity an entity of a proposal.
     * @return the resulting projection.
     */
    Proposal project(ProposalEntity entity);
}
