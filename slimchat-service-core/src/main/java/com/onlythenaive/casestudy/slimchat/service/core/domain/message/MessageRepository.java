package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.EntityRepository;

/**
 * Chat message repository.
 *
 * @author Ilia Gubarev
 */
public interface MessageRepository extends EntityRepository<MessageEntity> {

    @Override
    default String getEntityName() {
        return MessageEntity.NAME;
    }
}
