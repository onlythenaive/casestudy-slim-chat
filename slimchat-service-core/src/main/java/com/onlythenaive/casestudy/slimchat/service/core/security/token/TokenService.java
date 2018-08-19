package com.onlythenaive.casestudy.slimchat.service.core.security.token;

/**
 * Security token service.
 *
 * @author Ilia Gubarev
 */
public interface TokenService {

    /**
     * Creates a new security token for specified account nickname.
     *
     * @param accountNickname the nickname of an existing account.
     * @return a new security token.
     */
    Token createToken(String accountNickname);

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
