package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import java.util.Optional;

/**
 * Security token provider.
 *
 * @author Ilia Gubarev
 */
public interface TokenProvider {

    /**
     * Finds an existing security token by its ID if any.
     *
     * @param id the token ID.
     * @return the requested security token.
     */
    Optional<Token> findById(String id);
}
