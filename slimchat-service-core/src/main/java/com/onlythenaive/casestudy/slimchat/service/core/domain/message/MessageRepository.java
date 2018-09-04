package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.EntityRepository;

/**
 * Chat message repository.
 *
 * @author Ilia Gubarev
 */
public interface MessageRepository extends EntityRepository<MessageEntity>, MessageRepositoryExtension {

    /**
     * Retrieves a page of message entities by specified thread's and observer's IDs.
     *
     * @param threadId the ID of a chat.
     * @param observerId the ID of an observer.
     * @param pageable a paging properties.
     * @return the resulting page.
     */
    Page<MessageEntity> findByThreadIdAndObserverIds(String threadId, String observerId, Pageable pageable);

    @Override
    default String getEntityName() {
        return MessageEntity.NAME;
    }
}
