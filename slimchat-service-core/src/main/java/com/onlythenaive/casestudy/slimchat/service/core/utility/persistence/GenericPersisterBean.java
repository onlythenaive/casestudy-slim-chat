package com.onlythenaive.casestudy.slimchat.service.core.utility.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Generic entity persister implementation.
 *
 * @param <E> the type of an entity.
 *
 * @author Ilia Gubarev
 */
public abstract class GenericPersisterBean<E extends PersistedEntity> extends GenericComponentBean
        implements EntityPersister<E> {

    @Autowired
    private EntityRepository<E> repository;

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
