package com.onlythenaive.casestudy.slimchat.service.core.domain.proposal;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericPersisterBean;

/**
 * Connection proposal persister implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProposalPersisterBean extends GenericPersisterBean<ProposalEntity> implements ProposalPersister {

    @Override
    public String getEntityName() {
        return ProposalEntity.NAME;
    }

    @Override
    public Class<ProposalEntity> getEntityType() {
        return ProposalEntity.class;
    }
}
