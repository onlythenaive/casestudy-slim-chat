package com.onlythenaive.casestudy.slimchat.service.core.utility.principal;

import java.util.Optional;

/**
 * Principal context.
 *
 * @author Ilia Gubarev
 */
public interface PrincipalContext {

    /**
     * Retrieves the current principal if any.
     *
     * @return the current principal.
     */
    Optional<Principal> getPrincipal();
}
