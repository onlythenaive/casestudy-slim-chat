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
     * @param loginKey the login key of an account.
     * @param loginSecret account's login secret.
     */
    void createFromAccountLoginPair(String loginKey, String loginSecret);

    /**
     * Deletes the current provided token.
     */
    void delete();
}
