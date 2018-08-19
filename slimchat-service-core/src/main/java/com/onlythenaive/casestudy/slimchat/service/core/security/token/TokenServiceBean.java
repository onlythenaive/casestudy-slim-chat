package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.generic.GenericComponentBean;

/**
 * Security token service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class TokenServiceBean extends GenericComponentBean implements TokenService {

    private Map<String, Token> tokens;

    @Override
    public Token createToken(String accountNickname) {
        Token token = Token.builder()
                .id(uuid())
                .accountNickname(accountNickname)
                .createdAt(now())
                .build();
        this.tokens.put(token.getId(), token);
        return token;
    }

    @Override
    public Token findTokenById(String id) {
        return this.tokens.get(id);
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
