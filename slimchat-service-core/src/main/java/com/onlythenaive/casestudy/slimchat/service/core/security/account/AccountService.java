package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import java.util.Optional;

/**
 * Security account service.
 *
 * @author Ilia Gubarev
 */
public interface AccountService {

    /**
     * Creates a new security account.
     *
     * @param name the account name.
     * @param password the account password
     * @return a new security account.
     */
    Account createAccount(String name, String password);

    /**
     * Finds an existing security account by its name.
     *
     * @param name the account name.
     * @return the requested security account if any.
     */
    Optional<Account> findAccountByName(String name);

    /**
     * Finds an existing security account by its ID.
     *
     * @param id the account ID.
     * @return the requested security account if any.
     */
    Optional<Account> findAccountById(String id);
}
