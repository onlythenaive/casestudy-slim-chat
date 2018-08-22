package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Security token repository.
 *
 * @author Ilia Gubarev
 */
public interface TokenRepository extends MongoRepository<TokenEntity, String> {

}
