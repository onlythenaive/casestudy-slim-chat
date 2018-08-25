package com.onlythenaive.casestudy.slimchat.service.core.utility.principal;

/**
 * Principal context configurator.
 *
 * @author Ilia Gubarev
 */
public interface PrincipalContextConfigurator extends PrincipalContext {

    /**
     * Set a new current principal.
     *
     * @param principal a new principal to be set.
     */
    void setPrincipal(Principal principal);
}
