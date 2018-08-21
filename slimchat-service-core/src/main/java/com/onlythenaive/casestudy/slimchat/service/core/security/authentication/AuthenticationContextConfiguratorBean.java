package com.onlythenaive.casestudy.slimchat.service.core.security.authentication;

import java.util.Optional;

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
    public Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(this.authentication);
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
        if (authentication == null) {
            logger().debug("Removed current authentication from the context");
        } else {
            logger().debug("Set a new authentication into the context");
        }
    }
}
