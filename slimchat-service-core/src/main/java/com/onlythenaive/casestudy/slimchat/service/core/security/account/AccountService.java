package com.onlythenaive.casestudy.slimchat.service.core.security.account;

/**
 * Security account service.
 *
 * @author Ilia Gubarev
 */
public interface AccountService {

    /**
     * Creates a new security account.
     *
     * @param nickname account nickname.
     * @param password account password
     * @return a new security account.
     */
    Account createAccount(String nickname, String password);

    /**
     * Finds an existing security account by its nickname.
     *
     * @param nickname account nickname.
     * @return the requested security account if it exists.
     */
    Account findAccountByNickname(String nickname);
}
