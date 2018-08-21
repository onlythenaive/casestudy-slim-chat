package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<AccountEntity, String> {

    Optional<AccountEntity> findById(String id);

    Optional<AccountEntity> findByName(String name);
}
