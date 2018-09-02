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
     * Checks if a security account exists with such login key.
     *
     * @param loginKey the login key of the account.
     * @return true if the account exists.
     */
    boolean existsByLoginKey(String loginKey);

    /**
     * Finds an existing account by its login key if any.
     *
     * @param loginKey the login key of the account.
     * @return the requested account.
     */
    Optional<AccountEntity> findByLoginKey(String loginKey);
}
