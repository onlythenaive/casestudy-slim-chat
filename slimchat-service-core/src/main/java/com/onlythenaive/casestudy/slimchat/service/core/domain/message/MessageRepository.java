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

    Page<MessageEntity> findByChatIdAndObserverIds(String chatId, String observerId, Pageable pageable);

    @Override
    default String getEntityName() {
        return MessageEntity.NAME;
    }
}
