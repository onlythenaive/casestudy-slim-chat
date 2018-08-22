package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<MessageEntity, String> {

}
