package com.onlythenaive.casestudy.slimchat.service.core.domain.shared;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Generic domain persister implementation.
 *
 * @param <E> the type of a domain entity.
 *
 * @author Ilia Gubarev
 */
public abstract class DomainPersisterBean<E extends DomainEntity> extends DomainComponentBean
        implements DomainPersister<E> {

    @Autowired
    private DomainRepository<E> repository;

    @Override
    public void insert(E entity) {
        if (entity.getId() == null) {
            entity.setId(uuid());
        }
        entity.setCreatedAt(now());
        entity.setLastModifiedAt(now());
        repository.insert(entity);
    }

    @Override
    public void update(E entity) {
        entity.setLastModifiedAt(now());
        this.repository.save(entity);
    }
}
