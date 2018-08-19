package com.onlythenaive.casestudy.slimchat.service.core.security.authentication;

/**
 * Security authentication context.
 *
 * @author Ilia Gubarev
 */
public interface AuthenticationContext {

    /**
     * Retrieves a current authentication.
     *
     * @return current authentication if it exists.
     */
    Authentication getAuthentication();
}
