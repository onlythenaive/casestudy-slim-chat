package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Optional;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainRepository;

/**
 * User profile repository.
 *
 * @author Ilia Gubarev
 */
public interface ProfileRepository extends GenericDomainRepository<ProfileEntity> {

    /**
     * Finds a user profile by the name of its referenced account.
     *
     * @param accountName the name of a referenced account.
     * @return user profile entity if any.
     */
    Optional<ProfileEntity> findByAccountName(String accountName);
}
