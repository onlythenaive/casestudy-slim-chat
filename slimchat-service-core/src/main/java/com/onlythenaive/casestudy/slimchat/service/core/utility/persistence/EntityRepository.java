package com.onlythenaive.casestudy.slimchat.service.core.utility.persistence;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;

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

    default E getById(String id) {
        return findById(id).orElseThrow(() -> notFoundById(id));
    }

    default OperationException notFoundById(String id) {
        return OperationException.builder()
                .category(ExceptionCategory.RESOURCE)
                .comment(String.format("Entity '%s' with ID '%s' not found", getEntityName(), id))
                .textcode(String.format("x.resource.%s.not-found", getEntityName()))
                .dataAttribute("id", id)
                .build();
    }

    default String getEntityName() {
        return "generic";
    }
}
