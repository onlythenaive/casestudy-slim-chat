package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.generic.GenericComponentBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Security token service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class TokenServiceBean extends GenericComponentBean implements TokenService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, Token> tokens;

    @Override
    public Token createToken(String accountId) {
        Token token = Token.builder()
                .id(uuid())
                .accountId(accountId)
                .createdAt(now())
                .build();
        this.tokens.put(token.getId(), token);
        logger.info("Token created: " + token.getId());
        return token;
    }

    @Override
    public Token findTokenById(String id) {
        Token token = this.tokens.get(id);
        if (token != null) {
            logger.info("Token found: " + token.getId());
        } else {
            logger.info("Token not found");
        }
        return token;
    }

    @Override
    public void removeTokenById(String id) {
        this.tokens.remove(id);
    }

    @PostConstruct
    private void init() {
        this.tokens = new HashMap<>();
    }
}
