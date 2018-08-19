package com.onlythenaive.casestudy.slimchat.service.core.utility.view;

/**
 * View service API.
 * <p/>
 * Renders HTML views.
 *
 * @author Ilia Gubarev
 */
public interface ViewService {

    /**
     * Renders an HTML view.
     *
     * @param name the name of a view to be rendered.
     * @return the result of view rendering.
     */
    String renderView(String name);

    /**
     * Renders an HTML view with specific content.
     *
     * @param name the name of a view to be rendered.
     * @param content a specific content to be used during view rendering.
     * @return the result of view rendering.
     */
    String renderView(String name, Object content);
}
