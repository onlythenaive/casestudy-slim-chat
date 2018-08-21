package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Security token service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class TokenServiceBean extends GenericComponentBean implements TokenService {

    private Map<String, Token> tokens;

    @Override
    public Token createToken(String accountId) {
        String id = uuid();
        Token token = Token.builder()
                .id(id)
                .accountId(accountId)
                .createdAt(now())
                .build();
        this.tokens.put(id, token);
        logger().debug("Created a new security token with ID = {}", id);
        return token;
    }

    @Override
    public Optional<Token> findTokenById(String id) {
        Token token = this.tokens.get(id);
        if (token != null) {
            logger().debug("Found existing security token with ID = {}", token.getId());
        } else {
            logger().debug("No existing security token found with ID = {}", id);
        }
        return Optional.ofNullable(token);
    }

    @Override
    public void removeTokenById(String id) {
        Token removedToken = this.tokens.remove(id);
        if (removedToken != null) {
            logger().debug("Removed existing security token with ID = {}", removedToken.getId());
        }
    }

    @PostConstruct
    private void init() {
        this.tokens = new HashMap<>();
    }
}
