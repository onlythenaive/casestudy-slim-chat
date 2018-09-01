package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericPersisterBean;

/**
 * Chat group persister implementation.
 *
 * @author Ili Gubarev
 */
@Service
public class GroupPersisterBean extends GenericPersisterBean<GroupEntity> implements GroupPersister {

    @Override
    public String getEntityName() {
        return "Group";
    }

    @Override
    public Class<GroupEntity> getEntityType() {
        return GroupEntity.class;
    }
}
