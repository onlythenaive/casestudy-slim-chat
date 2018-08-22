package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<MessageEntity, String> {

    @Override
    Collection<MessageEntity> findAllById(Iterable<String> ids);
}
