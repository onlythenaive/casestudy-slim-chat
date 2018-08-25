package com.onlythenaive.casestudy.slimchat.service.core.security.token;

/**
 * Security token context configurator.
 *
 * @author Ilia Gubarev
 */
public interface TokenContextConfigurator extends TokenContext {

    /**
     * Sets a new generated token.
     *
     * @param generatedToken a new generated token.
     */
    void setGenerated(Token generatedToken);

    /**
     * Sets a new provided token.
     *
     * @param providedToken a new provided token.
     */
    void setProvided(Token providedToken);
}
