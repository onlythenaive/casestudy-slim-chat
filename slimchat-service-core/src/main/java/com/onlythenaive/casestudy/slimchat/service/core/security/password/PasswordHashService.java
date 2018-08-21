package com.onlythenaive.casestudy.slimchat.service.core.security.password;

/**
 * Security account password service.
 *
 * @author Ilia Gubarev
 */
public interface PasswordHashService {

    /**
     * Generates a hash from specified account password.
     *
     * @param password account password.
     * @return password hash.
     */
    String hash(String password);

    /**
     * Verifies specified account password against provided hash.
     *
     * @param password account password to be verified.
     * @param passwordHash password hash.
     * @return true if verification is successful.
     */
    boolean verify(String password, String passwordHash);
}
