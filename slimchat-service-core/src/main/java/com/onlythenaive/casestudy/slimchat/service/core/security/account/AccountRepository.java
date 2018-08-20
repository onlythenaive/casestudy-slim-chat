package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<AccountEntity, String> {

    AccountEntity getById(String id);

    AccountEntity getByName(String name);
}
