package com.onlythenaive.casestudy.slimchat.service.core.utility.persistence;

/**
 * Generic entity persister.
 *
 * @param <E> the type of a persisted entity.
 *
 * @author Ilia Gubarev
 */
public interface EntityPersister<E extends PersistedEntity> {

    /**
     * Inserts a new entity into the data storage.
     *
     * @param entity the entity to be persisted.
     */
    void insert(E entity);

    /**
     * Updates an existing entity in the data storage.
     *
     * @param entity the entity to be persisted.
     */
    void update(E entity);
}
