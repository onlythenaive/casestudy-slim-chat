package com.onlythenaive.casestudy.slimchat.service.core.security.authentication;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.Token;

/**
 * Authentication service.
 * <p/>
 * Handles authentication state for a current request.
 *
 * @author Ilia Gubarev
 */
public interface AuthenticationService {

    /**
     * Setups a new authentication to the current request.
     *
     * @param account authenticated account.
     * @param token authentication-corresponding token.
     * @return the current authentication.
     */
    Authentication createCurrentAuthentication(Account account, Token token);

    /**
     * Removes an existing authentication from the current request.
     */
    void removeCurrentAuthentication();
}
