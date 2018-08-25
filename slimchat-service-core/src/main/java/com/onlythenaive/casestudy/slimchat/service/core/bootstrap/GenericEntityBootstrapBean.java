package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.EntityPersister;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.EntityRepository;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.PersistedEntity;

public abstract class GenericEntityBootstrapBean<E extends PersistedEntity> extends GenericBootstrapBean {

    @Autowired
    private EntityRepository<E> repository;

    @Autowired
    private EntityPersister<E> persister;

    protected EntityRepository<E> getRepository() {
        return this.repository;
    }

    protected EntityPersister<E> getPersister() {
        return this.persister;
    }

    protected void insertBootstrappedEntity(E entity) {
        if (entity.getId() == null) {
            entity.setId(uuid());
        }
        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(now());
        }
        if (entity.getLastModifiedAt() == null) {
            entity.setLastModifiedAt(now());
        }
        this.persister.insert(entity);
    }
}
