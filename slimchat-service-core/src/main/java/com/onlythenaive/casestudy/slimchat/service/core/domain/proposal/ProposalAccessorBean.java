package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericAccessorBean;

/**
 * Connection proposal accessor implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProposalAccessorBean extends GenericAccessorBean<ProposalEntity> implements ProposalAccessor {

    @Override
    public AccessLevel allowedAccessLevel(ProposalEntity subject) {
        if (subject.getInitiatorId().equals(principalId()) || subject.getAcceptorId().equals(principalId())) {
            return AccessLevel.EDIT;
        }
        return AccessLevel.BYPASS;
    }

    @Override
    protected String getEntityName() {
        return "proposal";
    }
}
