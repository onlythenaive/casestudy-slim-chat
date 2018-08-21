package com.onlythenaive.casestudy.slimchat.service.core.security;

/**
 * Security service.
 *
 * @author Ilia Gubarev
 */
public interface SecurityService {

    void authenticateByTokenId(String tokenId);

    /**
     * Creates a new security account.
     *
     * @param name account name.
     * @param password account password.
     */
    void createAccount(String name, String password);

    /**
     * Creates a new authentication and security token.
     *
     * @param name account nickname.
     * @param password account password.
     */
    void login(String name, String password);

    /**
     * Removes current authentication and security token.
     */
    void logout();
}
