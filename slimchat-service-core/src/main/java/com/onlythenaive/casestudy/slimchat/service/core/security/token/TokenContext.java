package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import java.util.Optional;

/**
 * Security token context.
 *
 * @author Ilia Gubarev
 */
public interface TokenContext {

    /**
     * Retrieves a generated security token if any.
     *
     * @return the generated token.
     */
    Optional<Token> getGenerated();

    /**
     * Retrieves a provided security token if any.
     *
     * @return the provided token.
     */
    Optional<Token> getProvided();
}
