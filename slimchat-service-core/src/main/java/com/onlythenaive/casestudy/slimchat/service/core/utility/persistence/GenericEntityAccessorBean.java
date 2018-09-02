package com.onlythenaive.casestudy.slimchat.service.core.utility.persistence;

import com.onlythenaive.casestudy.slimchat.service.core.utility.access.GenericAccessorBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;

/**
 * Generic persisted entity accessor implementation.
 *
 * @param <E> the type of a persisted entity.
 *
 * @author Ilia Gubarev
 */
public abstract class GenericEntityAccessorBean<E extends PersistedEntity> extends GenericAccessorBean<E> {

    @Override
    protected OperationException accessDenied(E entity) {
        // TODO: improve the exception
        return insufficientPrivileges();
    }
}
