package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Security token generator implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class TokenGeneratorBean extends GenericComponentBean implements TokenGenerator {

    @Autowired
    private TokenContextConfigurator tokenContextConfigurator;

    @Autowired
    private TokenPersister tokenPersister;

    @Autowired
    private TokenProjector tokenProjector;

    @Override
    public Token generateForAccountId(String accountId) {
        TokenEntity entity = generate(accountId);
        this.tokenPersister.insert(entity);
        Token token = this.tokenProjector.project(entity);
        this.tokenContextConfigurator.setGenerated(token);
        logger().debug("Created a new security token with ID = {}", token.getId());
        return token;
    }

    private TokenEntity generate(String accountId) {
        return TokenEntity.builder()
                .id(uuid())
                .accountId(accountId)
                .createdAt(now())
                .build();
    }
}
