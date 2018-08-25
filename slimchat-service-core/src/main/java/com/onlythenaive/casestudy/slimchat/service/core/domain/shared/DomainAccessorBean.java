package com.onlythenaive.casestudy.slimchat.service.core.domain.shared;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationContext;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.EntityAccessor;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericAccessorBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.PersistedEntity;

/**
 * Generic domain entity accessor implementation.
 *
 * @param <E> the type of a domain entity.
 *
 * @author Ilia Gubarev
 */
public abstract class DomainAccessorBean<E extends PersistedEntity> extends GenericAccessorBean<E>
        implements EntityAccessor<E> {

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
