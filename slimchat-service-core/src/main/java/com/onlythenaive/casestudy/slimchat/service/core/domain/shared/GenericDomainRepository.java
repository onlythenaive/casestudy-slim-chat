package com.onlythenaive.casestudy.slimchat.service.core.domain.shared;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Generic domain entity repository.
 *
 * @param <E> the type of a domain entity.
 *
 * @author Ilia Gubarev
 */
@NoRepositoryBean
public interface GenericDomainRepository<E> extends MongoRepository<E, String> {

    @Override
    Collection<E> findAllById(Iterable<String> ids);
}
