package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Security token provider implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class TokenProviderBean extends GenericComponentBean implements TokenProvider {

    @Autowired
    private TokenProjector tokenProjector;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Optional<Token> findById(String id) {
        return this.tokenRepository.findById(id).map(this.tokenProjector::project);
    }
}
