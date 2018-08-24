package com.onlythenaive.casestudy.slimchat.service.core.domain.shared;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Generic domain entity accessor implementation.
 *
 * @param <E> the type of a domain entity.
 *
 * @author Ilia Gubarev
 */
public abstract class DomainAccessorBean<E> extends DomainComponentBean
        implements DomainAccessor<E> {

    @Autowired
    private DomainRepository<E> repository;

    @Override
    public E accessById(AccessLevel level, String id) {
        return accessByIdIfAny(level, id).orElseThrow(() -> notFoundById(entityName(), id));
    }

    @Override
    public Optional<E> accessByIdIfAny(AccessLevel level, String id) {
        return accessIfAny(level, () -> this.repository.findById(id));
    }

    /**
     * Gets the name of the domain entity.
     *
     * @return the entity name.
     */
    protected abstract String entityName();

    private Optional<E> accessIfAny(AccessLevel level, Supplier<Optional<E>> supplier) {
        Optional<E> optionalSubject = supplier.get();
        optionalSubject.ifPresent(subject -> ensureAccess(level, subject));
        return optionalSubject;
    }
}
