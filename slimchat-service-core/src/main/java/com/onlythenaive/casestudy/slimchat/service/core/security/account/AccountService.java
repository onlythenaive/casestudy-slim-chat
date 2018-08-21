package com.onlythenaive.casestudy.slimchat.service.core.security.account;

/**
 * Security account service.
 *
 * @author Ilia Gubarev
 */
public interface AccountService {

    /**
     * Creates a new security account.
     *
     * @param name account name.
     * @param password account password
     * @return a new security account.
     */
    Account createAccount(String name, String password);

    /**
     * Finds an existing security account by its ID.
     *
     * @param id account ID.
     * @return the requested security account if it exists.
     */
    Account getAccountById(String id);

    /**
     * Finds an existing security account by its name.
     *
     * @param name account name.
     * @return the requested security account if it exists.
     */
    Account getAccountByName(String name);
}
