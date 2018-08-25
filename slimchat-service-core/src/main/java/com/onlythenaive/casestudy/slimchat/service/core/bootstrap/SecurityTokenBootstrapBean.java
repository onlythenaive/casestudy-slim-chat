package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

import com.onlythenaive.casestudy.slimchat.service.core.security.token.TokenRepository;

@BootstrapComponent
@DependsOn("securityAccountBootstrapBean")
public class SecurityTokenBootstrapBean extends GenericBootstrapBean {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    protected String getBootstrapName() {
        return "security tokens";
    }

    @Override
    protected void executeBootstrap() {

    }
}
