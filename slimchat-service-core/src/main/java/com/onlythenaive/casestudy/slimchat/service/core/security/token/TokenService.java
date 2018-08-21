package com.onlythenaive.casestudy.slimchat.service.core.security.token;

/**
 * Security token service.
 *
 * @author Ilia Gubarev
 */
public interface TokenService {

    /**
     * Creates a new security token.
     *
     * @param accountId the ID of an existing account.
     * @return a new security token.
     */
    Token createToken(String accountId);

    /**
     * Finds an existing security token by its ID.
     *
     * @param id the ID of a token.
     * @return the requested token if it exists.
     */
    Token findTokenById(String id);

    /**
     * Removes an existing security token by its ID.
     *
     * @param id the ID of a token.
     */
    void removeTokenById(String id);
}
