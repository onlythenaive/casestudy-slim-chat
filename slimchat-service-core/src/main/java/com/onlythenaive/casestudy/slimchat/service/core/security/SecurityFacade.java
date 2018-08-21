package com.onlythenaive.casestudy.slimchat.service.core.security;

public interface SecurityFacade {

    /**
     * Authenticates current request by the provided account credentials.
     *
     * @param name the account name.
     * @param password the account password.
     */
    void authenticateByAccountCredentials(String name, String password);

    /**
     * Creates a new security account.
     *
     * @param name account name.
     * @param password account password.
     */
    void createAccount(String name, String password);

    /**
     * De-authenticates current request.
     */
    void deauthenticate();
}
