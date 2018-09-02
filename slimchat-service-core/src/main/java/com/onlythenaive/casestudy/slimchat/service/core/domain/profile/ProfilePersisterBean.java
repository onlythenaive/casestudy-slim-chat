package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.proposal.ProposalEntity;
import com.onlythenaive.casestudy.slimchat.service.core.utility.hash.Md5HashService;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericPersisterBean;

/**
 * User profile persister implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfilePersisterBean extends GenericPersisterBean<ProfileEntity> implements ProfilePersister {

    @Autowired
    private Md5HashService hashService;

    @Override
    public String getEntityName() {
        return ProposalEntity.NAME;
    }

    @Override
    public Class<ProfileEntity> getEntityType() {
        return ProfileEntity.class;
    }

    @Override
    protected void beforeInsert(ProfileEntity entity) {
        super.beforeInsert(entity);
        entity.setEmailHash(emailHash(entity.getEmail()));
    }

    private String emailHash(String email) {
        return email == null ? null : this.hashService.hash(email);
    }
}
