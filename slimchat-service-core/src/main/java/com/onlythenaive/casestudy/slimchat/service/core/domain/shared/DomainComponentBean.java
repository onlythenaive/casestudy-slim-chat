package com.onlythenaive.casestudy.slimchat.service.core.domain.shared;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationContext;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Generic domain component implementation.
 *
 * @author Ili Gubarev
 */
public abstract class DomainComponentBean extends GenericComponentBean {

    @Autowired
    private AuthenticationContext authenticationContext;

    /**
     * Retrieves the current principal.
     *
     * @return the current principal.
     */
    protected Account principal() {
        return this.authenticationContext.getAuthentication().orElseThrow(this::notAuthenticated).getAccount();
    }

    /**
     * Retrieves the ID of the current principal.
     *
     * @return the ID of the current principal.
     */
    protected String principalId() {
        return principal().getId();
    }
}
