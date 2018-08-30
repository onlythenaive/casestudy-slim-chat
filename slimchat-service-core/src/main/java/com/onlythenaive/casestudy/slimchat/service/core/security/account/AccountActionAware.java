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
     * @param event an event of account creation.
     */
    default void onAccountCreated(AccountCreationEvent event) {

    }
}
