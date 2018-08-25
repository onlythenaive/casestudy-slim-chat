package com.onlythenaive.casestudy.slimchat.service.core.security.account;

/**
 * Security account facade.
 *
 * @author Ilia Gubarev
 */
public interface AccountFacade {

    /**
     * Creates a new security account.
     *
     * @param name account name.
     * @param password account password.
     */
    void create(String name, String password);
}
