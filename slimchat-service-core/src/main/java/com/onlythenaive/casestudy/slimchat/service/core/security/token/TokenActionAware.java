package com.onlythenaive.casestudy.slimchat.service.core.security.token;

/**
 * Component aware of token-related actions.
 *
 * @author Ilia Gubarev
 */
public interface TokenActionAware {

    /**
     * Handles token creation.
     *
     * @param token a new created token.
     */
    default void onTokenCreated(Token token) {

    }

    /**
     * Handles token deletion.
     *
     * @param token a deleted created token.
     */
    default void onTokenDeleted(Token token) {

    }
}
