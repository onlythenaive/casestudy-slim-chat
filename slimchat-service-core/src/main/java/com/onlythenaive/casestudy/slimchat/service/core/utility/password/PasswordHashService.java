package com.onlythenaive.casestudy.slimchat.service.core.utility.password;

/**
 * Password hash service.
 *
 * @author Ilia Gubarev
 */
public interface PasswordHashService {

    /**
     * Generates a hash from specified password.
     *
     * @param password a password to be processed.
     * @return password hash.
     */
    String hash(String password);

    /**
     * Verifies specified password against provided hash.
     *
     * @param password a password to be verified.
     * @param passwordHash password hash.
     * @return true if verification is successful.
     */
    boolean verify(String password, String passwordHash);
}
