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
     * @param credentials account security credentials.
     */
    void createAccount(SecurityCredentials credentials);

    /**
     * Creates a new authentication and security token.
     *
     * @param credentials account security credentials.
     */
    void login(SecurityCredentials credentials);

    /**
     * Removes current authentication and security token.
     */
    void logout();
}
