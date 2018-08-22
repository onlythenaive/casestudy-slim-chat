package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Security token service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class TokenServiceBean extends GenericComponentBean implements TokenService {

    @Autowired
    private TokenProjector tokenProjector;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token createToken(String accountId) {
        String id = uuid();
        TokenEntity entity = TokenEntity.builder()
                .id(id)
                .accountId(accountId)
                .createdAt(now())
                .build();
        this.tokenRepository.insert(entity);
        logger().debug("Created a new security token with ID = {}", id);
        return project(entity);
    }

    @Override
    public Optional<Token> findTokenById(String id) {
        return this.tokenRepository.findById(id).map(this::project);
    }

    @Override
    public void removeTokenById(String id) {
        if (this.tokenRepository.existsById(id)) {
            this.tokenRepository.deleteById(id);
            logger().debug("Removed existing security token with ID = {}", id);
        }
    }

    private Token project(TokenEntity entity) {
        return this.tokenProjector.intoToken(entity);
    }
}
