package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import java.util.Collection;

import org.springframework.context.annotation.DependsOn;

import com.onlythenaive.casestudy.slimchat.service.core.security.token.TokenEntity;

@BootstrapComponent
@DependsOn("securityAccountBootstrapBean")
public class SecurityTokenBootstrapBean extends GenericEntityBootstrapBean<TokenEntity> {

    @Override
    protected String getBootstrapName() {
        return "security tokens";
    }

    @Override
    protected void executeBootstrap() {
        Collection<TokenEntity> entities = parseList("/bootstrap/dev/tokens.json", TokenEntity.class);
        entities.forEach(this::insertBootstrappedEntity);
    }
}
