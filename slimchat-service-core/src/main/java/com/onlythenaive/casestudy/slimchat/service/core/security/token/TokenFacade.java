package com.onlythenaive.casestudy.slimchat.service.core.security.token;

/**
 * Security token operations facade.
 *
 * @author Ilia Gubarev
 */
public interface TokenFacade {

    /**
     * Creates a new security token.
     *
     * @param name account name.
     * @param password account password.
     */
    void createFromAccountCredentials(String name, String password);

    /**
     * Deletes an existing token by iys ID
     *
     * @param id the ID of an existing token.
     */
    void deleteById(String id);
}
