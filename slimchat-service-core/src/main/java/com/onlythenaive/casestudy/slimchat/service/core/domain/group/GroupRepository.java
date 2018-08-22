package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<GroupEntity, String> {

}
