package com.onlythenaive.casestudy.slimchat.service.core.security.account;

/**
 * Component aware of account-related actions.
 *
 * @author Ilia Gubarev
 */
public interface AccountActionAware {

    /**
     * Handles account creation.
     *
     * @param account a new created account.
     */
    default void onAccountCreated(Account account) {

    }
}
