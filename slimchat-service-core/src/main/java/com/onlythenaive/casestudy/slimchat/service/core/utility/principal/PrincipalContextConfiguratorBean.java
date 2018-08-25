package com.onlythenaive.casestudy.slimchat.service.core.utility.principal;

import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Principal context configurator implementation.
 *
 * @author Ilia Gubarev
 */
@Component
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.INTERFACES)
public class PrincipalContextConfiguratorBean implements PrincipalContextConfigurator {

    private Principal principal;

    @Override
    public Optional<Principal> getPrincipal() {
        return Optional.ofNullable(this.principal);
    }

    @Override
    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }
}
