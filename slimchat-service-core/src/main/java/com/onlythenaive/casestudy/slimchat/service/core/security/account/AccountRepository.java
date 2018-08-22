package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Security account repository.
 *
 * @author Ilia Gubarev
 */
public interface AccountRepository extends MongoRepository<AccountEntity, String> {

    /**
     * Finds an account entity by its name.
     *
     * @param name the name of account.
     * @return account entity if any.
     */
    Optional<AccountEntity> findByName(String name);
}
