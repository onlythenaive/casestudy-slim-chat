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
     * @param accountId the ID of an account.
     * @param accountSecret account's secret.
     */
    void createFromAccountCredentials(String accountId, String accountSecret);

    /**
     * Deletes the current provided token.
     */
    void delete();
}
