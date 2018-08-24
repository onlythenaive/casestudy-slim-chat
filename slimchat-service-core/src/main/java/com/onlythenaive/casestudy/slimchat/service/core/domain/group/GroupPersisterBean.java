package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;

/**
 * Chat group persister implementation.
 *
 * @author Ili Gubarev
 */
@Service
public class GroupPersisterBean extends GenericDomainComponentBean implements GroupPersister {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public void insert(GroupEntity entity) {
        if (entity.getId() == null) {
            entity.setId(uuid());
        }
        entity.setLastModifiedAt(now());
        entity.setCreatedAt(now());
        this.groupRepository.insert(entity);
    }

    @Override
    public void update(GroupEntity entity) {
        entity.setLastModifiedAt(now());
        this.groupRepository.save(entity);
    }
}
