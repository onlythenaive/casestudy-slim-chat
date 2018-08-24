package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainPersisterBean;

/**
 * Chat message persister implementation.
 *
 * @author Ili Gubarev
 */
@Service
public class MessagePersisterBean extends DomainPersisterBean<MessageEntity> implements MessagePersister {

}
