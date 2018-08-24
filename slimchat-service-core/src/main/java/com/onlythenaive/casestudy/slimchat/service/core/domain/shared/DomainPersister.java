package com.onlythenaive.casestudy.slimchat.service.core.domain.shared;

/**
 * Generic domain persister.
 *
 * @param <E> the type of a domain entity.
 *
 * @author Ilia Gubarev
 */
public interface DomainPersister<E extends DomainEntity> {

    /**
     * Inserts a new domain entity into the data storage.
     *
     * @param entity the domain entity to be persisted.
     */
    void insert(E entity);

    /**
     * Updates an existing domain entity in the data storage.
     *
     * @param entity the domain entity to be persisted.
     */
    void update(E entity);
}
