package com.onlythenaive.casestudy.slimchat.service.core.security.authentication;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Security authentication context configurator implementation.
 *
 * @author Ilia Gubarev
 */
@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class AuthenticationContextConfiguratorBean extends GenericComponentBean
        implements AuthenticationContextConfigurator {

    private Authentication authentication;

    @Override
    public Authentication getAuthentication() {
        return this.authentication;
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
}
