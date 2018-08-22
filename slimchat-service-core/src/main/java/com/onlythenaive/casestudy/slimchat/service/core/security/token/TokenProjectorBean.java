package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Security token projector.
 *
 * @author Ilia Gubarev
 */
@Service
public class TokenProjectorBean extends GenericComponentBean implements TokenProjector {

    @Override
    public Token intoToken(TokenEntity entity) {
        return Token.builder()
                .id(entity.getId())
                .accountId(entity.getAccountId())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
