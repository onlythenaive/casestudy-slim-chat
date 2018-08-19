package com.onlythenaive.casestudy.slimchat.service.core.generic;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlythenaive.casestudy.slimchat.service.core.utility.view.ViewService;

/**
 * Generic controller implementation.
 *
 * @author Ilia Gubarev
 */
public abstract class GenericControllerBean extends GenericComponentBean {

    @Autowired
    private ViewService viewService;

    /**
     * Makes a current request to be redirected to specified URI.
     *
     * @param uri the target URI for request redirection.
     * @return an empty string.
     */
    protected String redirect(String uri) {
        // TODO: implement redirection
        throw new UnsupportedOperationException();
    }

    /**
     * Renders an HTML view.
     *
     * @param name the name of a view to be rendered.
     * @return the result of view rendering.
     */
    protected String renderView(String name) {
        return this.viewService.renderView(name);
    }

    /**
     * Renders an HTML view with specific content.
     *
     * @param name the name of a view to be rendered.
     * @param content a specific content to be used during view rendering.
     * @return the result of view rendering.
     */
    protected String renderView(String name, Object content) {
        return this.viewService.renderView(name, content);
    }
}
