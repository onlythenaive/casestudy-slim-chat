package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainRepository;

public interface MessageRepository extends DomainRepository<MessageEntity> {

    @Override
    Collection<MessageEntity> findAllById(Iterable<String> ids);
}
