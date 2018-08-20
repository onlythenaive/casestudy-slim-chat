package com.onlythenaive.casestudy.slimchat.service.core.security;

/**
 * Security service.
 *
 * @author Ilia Gubarev
 */
public interface SecurityService {

    /**
     * Creates a new security account.
     *
     * @param nickname account nickname.
     * @param password account password.
     */
    void createAccount(String nickname, String password);

    void authenticateByToken(String tokenId);

    /**
     * Creates a new authentication and security token.
     *
     * @param nickname account nickname.
     * @param password account password.
     */
    void login(String nickname, String password);

    /**
     * Removes current authentication and security token.
     */
    void logout();
}
