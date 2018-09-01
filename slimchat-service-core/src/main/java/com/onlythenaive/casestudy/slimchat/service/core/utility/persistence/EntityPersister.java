package com.onlythenaive.casestudy.slimchat.service.core.utility.persistence;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;

/**
 * Generic entity persister.
 *
 * @param <E> the type of a persisted entity.
 *
 * @author Ilia Gubarev
 */
public interface EntityPersister<E extends PersistedEntity> {

    /**
     * Gets the name of persisted entities.
     *
     * @return the name of persisted entities.
     */
    String getEntityName();

    /**
     * Gets the type of persisted entities.
     *
     * @return the type of persisted entities.
     */
    Class<E> getEntityType();

    /**
     * Deletes an existing entity by its ID.
     *
     * @param id the ID of an entity to be deleted.
     */
    void delete(String id);

    /**
     * Deletes multiple existing entities by their IDs.
     *
     * @param ids IDs of entities to be deleted.
     */
    void delete(Iterable<String> ids);

    /**
     * Inserts a new entity into the data storage.
     *
     * @param entity the entity to be persisted.
     * @return the resulting entity.
     */
    E insert(E entity);

    /**
     * Updates an existing entity in the data storage.
     *
     * @param entity the entity to be updated.
     * @return the resulting entity.
     */
    E update(E entity);

    /**
     * Updates multiple existing entities by applying specified update.
     *
     * @param criteria a criteria to be used for querying entities for the update.
     * @param update an update to be applied.
     * @return the result of the update.
     */
    UpdateResult update(Criteria criteria, Update update);
}
