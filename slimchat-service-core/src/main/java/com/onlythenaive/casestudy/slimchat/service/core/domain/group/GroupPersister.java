package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

/**
 * Chat group persister.
 *
 * @author Ili Gubarev
 */
public interface GroupPersister {

    void insert(GroupEntity entity);

    void update(GroupEntity entity);
}
