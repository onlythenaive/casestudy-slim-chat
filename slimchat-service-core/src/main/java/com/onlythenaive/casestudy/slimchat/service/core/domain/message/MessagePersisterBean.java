package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericPersisterBean;

/**
 * Chat message persister implementation.
 *
 * @author Ili Gubarev
 */
@Service
public class MessagePersisterBean extends GenericPersisterBean<MessageEntity> implements MessagePersister {

    @Override
    public String getEntityName() {
        return MessageEntity.NAME;
    }

    @Override
    public Class<MessageEntity> getEntityType() {
        return MessageEntity.class;
    }

    @Override
    protected void beforeInsert(MessageEntity entity) {
        super.beforeInsert(entity);
        entity.setChatDescriptor(MessageChatDescriptorBuilder.of(entity).build());
    }
}
