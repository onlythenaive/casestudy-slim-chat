package com.onlythenaive.casestudy.slimchat.service.core.security.token;

/**
 * Security token generator.
 *
 * @author Ilia Gubarev
 */
public interface TokenGenerator {

    /**
     * Generates a new security token for specified account ID.
     *
     * @param accountId the ID of an account.
     * @return the generated token.
     */
    Token generateForAccountId(String accountId);
}
