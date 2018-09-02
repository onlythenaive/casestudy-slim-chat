package com.onlythenaive.casestudy.slimchat.service.core.utility.access;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;

/**
 * Generic access checker implementation.
 *
 * @param <T> the type of a subject.
 *
 * @author Ilia Gubarev
 */
public abstract class GenericAccessorBean<T> extends GenericComponentBean implements Accessor<T> {

    @Override
    public void ensureAccess(T subject, AccessLevel level) {
        if (getBypassThreshold().greaterOrEqual(level)) {
            return;
        }
        if (level.greaterThan(allowedAccessLevel(subject))) {
            throw accessDenied(subject);
        }
    }

    /**
     * Gets a bypass level.
     *
     * @return a bypass level.
     */
    protected AccessLevel getBypassThreshold() {
        return AccessLevel.BYPASS;
    }

    /**
     * Creates an exception for insufficient privileges.
     *
     * @param subject a subject that triggered the exception.
     * @return a new operation exception to be thrown.
     */
    protected OperationException accessDenied(T subject) {
        // NOTE: default implementation just throws a common exception
        return insufficientPrivileges();
    }
}
