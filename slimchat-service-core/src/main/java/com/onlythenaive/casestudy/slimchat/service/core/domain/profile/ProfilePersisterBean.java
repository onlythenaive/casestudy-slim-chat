package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericPersisterBean;

/**
 * User profile persister implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfilePersisterBean extends GenericPersisterBean<ProfileEntity> implements ProfilePersister {

    @Override
    public String getEntityName() {
        return "Profile";
    }

    @Override
    public Class<ProfileEntity> getEntityType() {
        return ProfileEntity.class;
    }
}
