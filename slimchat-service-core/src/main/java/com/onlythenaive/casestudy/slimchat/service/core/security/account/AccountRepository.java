package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import java.util.Optional;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.EntityRepository;

/**
 * Security account repository.
 *
 * @author Ilia Gubarev
 */
public interface AccountRepository extends EntityRepository<AccountEntity> {

    /**
     * Checks if an account with specified name already exists.
     *
     * @param name an account name to be checked.
     * @return true if an account already exists.
     */
    boolean existsByName(String name);

    /**
     * Finds an account entity by its name if any.
     *
     * @param name the name of account.
     * @return account entity if any.
     */
    Optional<AccountEntity> findByName(String name);
}
