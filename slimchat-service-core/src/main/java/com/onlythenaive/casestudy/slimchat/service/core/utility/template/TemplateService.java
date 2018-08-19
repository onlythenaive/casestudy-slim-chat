package com.onlythenaive.casestudy.slimchat.service.core.utility.template;

import java.util.Map;

/**
 * Template service.
 * <p/>
 * Renders text-based templates.
 *
 * @author Ilia Gubarev
 */
public interface TemplateService {

    /**
     * Renders a predefined template.
     *
     * @param name the name of a predefined template to re rendered.
     * @param properties data (context) to be used during template rendering.
     * @return the result of template rendering.
     */
    String renderTemplate(String name, Map<String, Object> properties);
}
