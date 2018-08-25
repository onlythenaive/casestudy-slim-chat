package com.onlythenaive.casestudy.slimchat.service.core.security.token;

/**
 * Security token projector.
 *
 * @author Ilia Gubarev
 */
public interface TokenProjector {

    /**
     * Converts a security token entity into its projection.
     *
     * @param entity an entity to be projected.
     * @return the resulting projection.
     */
    Token project(TokenEntity entity);
}
