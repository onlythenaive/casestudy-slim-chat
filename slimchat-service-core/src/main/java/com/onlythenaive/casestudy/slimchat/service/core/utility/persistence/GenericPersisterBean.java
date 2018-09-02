package com.onlythenaive.casestudy.slimchat.service.core.utility.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.UpdateResult;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import org.bson.BsonValue;

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
    private MongoTemplate mongoTemplate;

    @Autowired
    private EntityRepository<E> repository;

    @Override
    public void delete(String id) {
        beforeDelete(id);
        this.repository.deleteById(id);
        afterDelete(id);
        logger().debug("Deleted a '{}' entity with ID = {}", getEntityName(), id);
    }

    @Override
    public void delete(Iterable<String> ids) {
        ids.forEach(this::beforeDelete);
        this.repository.deleteById(ids);
        ids.forEach(this::afterDelete);
        logger().debug("Deleted multiple '{}' entities with ID = {}", getEntityName(), ids);
    }

    @Override
    public E insert(E entity) {
        beforeInsert(entity);
        E insertedEntity = repository.insert(entity);
        afterInsert(insertedEntity);
        logger().debug("Inserted a new '{}' entity with ID = {}", getEntityName(), entity.getId());
        return insertedEntity;
    }

    @Override
    public E update(E entity) {
        beforeUpdate(entity);
        E updatedEntity = this.repository.save(entity);
        afterUpdate(updatedEntity);
        logger().debug("Updated a '{}' entity with ID = {}", getEntityName(), entity.getId());
        return updatedEntity;
    }

    @Override
    public UpdateResult update(Criteria criteria, Update update) {
        UpdateResult result = this.mongoTemplate.updateMulti(Query.query(criteria), update, getEntityType());

        BsonValue upsertedId = result.getUpsertedId();
        if (upsertedId != null) {
            logger().debug("Updated multiple '{}' entity with ID = {}", getEntityName(), upsertedId);
        }
        return result;
    }

    /**
     * Hooks entity lifecycle after it's deleted.
     *
     * @param id the ID of an affected entity.
     */
    protected void afterDelete(String id) {
        // NOTE: Default implementation does nothing.
    }

    /**
     * Hooks entity lifecycle after it's inserted.
     *
     * @param entity an affected entity.
     */
    protected void afterInsert(E entity) {
        // NOTE: Default implementation does nothing.
    }

    /**
     * Hooks entity lifecycle after it's updated.
     *
     * @param entity an affected entity.
     */
    protected void afterUpdate(E entity) {
        // NOTE: Default implementation does nothing.
    }

    /**
     * Hooks entity lifecycle before it's deleted.
     *
     * @param id the ID of an affected entity.
     */
    protected void beforeDelete(String id) {
        // NOTE: Default implementation does nothing.
    }

    /**
     * Hooks entity lifecycle before it's inserted.
     *
     * @param entity an affected entity.
     */
    protected void beforeInsert(E entity) {
        if (entity.getId() == null) {
            entity.setId(uuid());
        }
        entity.setCreatedAt(now());
        entity.setLastModifiedAt(now());
    }

    /**
     * Hooks entity lifecycle before it's updated.
     *
     * @param entity an affected entity.
     */
    protected void beforeUpdate(E entity) {
        entity.setLastModifiedAt(now());
    }
}
