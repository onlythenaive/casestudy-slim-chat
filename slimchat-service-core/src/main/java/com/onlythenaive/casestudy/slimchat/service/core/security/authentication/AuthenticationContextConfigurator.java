package com.onlythenaive.casestudy.slimchat.service.core.security.authentication;

/**
 * Security authentication context configurator.
 *
 * @author Ilia Gubarev
 */
public interface AuthenticationContextConfigurator extends AuthenticationContext {

    /**
     * Sets current authentication.
     *
     * @param authentication authentication to be set.
     */
    void setAuthentication(Authentication authentication);
}
