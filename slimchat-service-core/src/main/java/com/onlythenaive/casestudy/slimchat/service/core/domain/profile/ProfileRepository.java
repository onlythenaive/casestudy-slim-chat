package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.EntityRepository;

/**
 * User profile repository.
 *
 * @author Ilia Gubarev
 */
public interface ProfileRepository extends EntityRepository<ProfileEntity> {

    @Override
    default String getEntityName() {
        return ProfileEntity.NAME;
    }
}
