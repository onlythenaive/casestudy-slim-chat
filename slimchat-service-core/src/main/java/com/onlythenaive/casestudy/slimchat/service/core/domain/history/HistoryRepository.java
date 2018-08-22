package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoryRepository extends MongoRepository<HistoryEntity, String> {

}
