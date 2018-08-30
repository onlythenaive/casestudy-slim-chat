package com.onlythenaive.casestudy.slimchat.service.core.utility.persistence;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Generic persisted entity accessor implementation.
 *
 * @param <E> the type of a persisted entity.
 *
 * @author Ilia Gubarev
 */
public abstract class GenericAccessorBean<E extends PersistedEntity> extends GenericComponentBean
        implements EntityAccessor<E> {

    @Autowired
    private EntityRepository<E> repository;

    @Override
    public E ensureAccess(AccessLevel level, E subject) {
        if (getBypassThreshold().greaterOrEqual(level)) {
            return subject;
        }
        if (level.greaterOrEqual(allowedAccessLevel(subject))) {
            throw insufficientPrivileges();
        }
        return subject;
    }

    @Override
    public E accessById(String id) {
        return accessById(getBypassThreshold(), id);
    }

    @Override
    public E accessById(AccessLevel level, String id) {
        return accessByIdIfAny(level, id).orElseThrow(() -> notFoundById(getEntityName(), id));
    }

    @Override
    public Optional<E> accessByIdIfAny(AccessLevel level, String id) {
        return accessIfAny(level, () -> this.repository.findById(id));
    }

    protected AccessLevel getBypassThreshold() {
        return AccessLevel.BYPASS;
    }

    /**
     * Gets the name of the domain entity.
     *
     * @return the entity name.
     */
    protected abstract String getEntityName();

    private Optional<E> accessIfAny(AccessLevel level, Supplier<Optional<E>> supplier) {
        Optional<E> optionalSubject = supplier.get();
        optionalSubject.ifPresent(subject -> ensureAccess(level, subject));
        return optionalSubject;
    }
}
