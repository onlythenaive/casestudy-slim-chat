package com.onlythenaive.casestudy.slimchat.service.core.security.authentication;

import java.util.Optional;

/**
 * Security authentication context.
 *
 * @author Ilia Gubarev
 */
public interface AuthenticationContext {

    /**
     * Retrieves a current authentication.
     *
     * @return current authentication if any.
     */
    Optional<Authentication> getAuthentication();
}
