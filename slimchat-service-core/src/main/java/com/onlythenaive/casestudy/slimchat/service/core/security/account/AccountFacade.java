package com.onlythenaive.casestudy.slimchat.service.core.security.account;

/**
 * Security account facade.
 *
 * @author Ilia Gubarev
 */
public interface AccountFacade {

    /**
     * Creates a new security account.
     *
     * @param invoice an invoice for account creation.
     */
    void create(AccountInvoice invoice);
}
