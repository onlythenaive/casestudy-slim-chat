package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import java.util.Optional;

/**
 * Security account provider.
 *
 * @author Ilia Gubarev
 */
public interface AccountProvider {

    /**
     * Finds an existing security account by its ID if any.
     *
     * @param id the account ID.
     * @return the requested security account.
     */
    Optional<Account> findById(String id);
}
