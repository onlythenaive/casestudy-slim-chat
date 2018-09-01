package com.onlythenaive.casestudy.slimchat.service.core.utility.persistence;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Generic entity repository.
 *
 * @param <E> the type of a persisted entity.
 *
 * @author Ilia Gubarev
 */
@NoRepositoryBean
public interface EntityRepository<E extends PersistedEntity> extends MongoRepository<E, String> {

    void deleteById(Iterable<String> ids);

    @Override
    Collection<E> findAllById(Iterable<String> ids);
}
