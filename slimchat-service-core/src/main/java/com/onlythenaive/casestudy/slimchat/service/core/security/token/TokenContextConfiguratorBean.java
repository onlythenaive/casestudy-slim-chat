package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Security token context configurator implementation.
 *
 * @author Ilia Gubarev
 */
@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class TokenContextConfiguratorBean implements TokenContextConfigurator {

    private Token generatedToken;
    private Token providedToken;

    @Override
    public Optional<Token> getGenerated() {
        return Optional.ofNullable(this.generatedToken);
    }

    @Override
    public void setGenerated(Token generatedToken) {
        this.generatedToken = generatedToken;
    }

    @Override
    public Optional<Token> getProvided() {
        return Optional.ofNullable(this.providedToken);
    }

    @Override
    public void setProvided(Token providedToken) {
        this.providedToken = providedToken;
    }
}
