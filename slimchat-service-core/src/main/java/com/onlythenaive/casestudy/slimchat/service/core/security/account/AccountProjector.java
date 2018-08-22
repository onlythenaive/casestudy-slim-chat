package com.onlythenaive.casestudy.slimchat.service.core.security.account;

/**
 * Security account projector.
 *
 * @author Ilia Gubarev
 */
public interface AccountProjector {

    /**
     * Converts a security account entity into its projection.
     *
     * @param entity an entity to be projected.
     * @return the resulting projection.
     */
    Account intoAccount(AccountEntity entity);
}
