package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import javax.annotation.PostConstruct;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Generic bootstrap component implementation.
 *
 * @author Ilia Gubarev
 */
public abstract class GenericBootstrapBean extends GenericComponentBean {

    protected abstract String getBootstrapName();

    protected abstract void executeBootstrap();

    protected void logBootstrap(String message, Object... objects) {
        logger().info("Bootstrap: " + message, objects);
    }

    @PostConstruct
    private void init() {
        logBootstrap("preparing to execute {} bootstrapping...", getBootstrapName());
        executeBootstrap();
        logBootstrap("bootstrapping of {} finished successfully", getBootstrapName());
    }
}
