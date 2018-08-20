package com.onlythenaive.casestudy.slimchat.service.core.domain.chat;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<ChatEntity, String> {

    ChatEntity getById(String id);
}
