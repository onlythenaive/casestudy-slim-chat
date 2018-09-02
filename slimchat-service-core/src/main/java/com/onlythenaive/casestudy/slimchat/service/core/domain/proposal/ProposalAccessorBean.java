package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.access.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericEntityAccessorBean;

/**
 * Connection proposal accessor implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProposalAccessorBean extends GenericEntityAccessorBean<ProposalEntity> implements ProposalAccessor {

    @Override
    public AccessLevel allowedAccessLevel(ProposalEntity entity) {
        if (entity.getInitiatorId().equals(principalId()) || entity.getAcceptorId().equals(principalId())) {
            return AccessLevel.MANAGE;
        }
        return AccessLevel.BYPASS;
    }
}
