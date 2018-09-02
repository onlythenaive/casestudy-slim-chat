package com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.helpers;

import com.github.jknack.handlebars.Helper;

/**
 * Template helper.
 *
 * @param <T> the type of helper's context.
 *
 * @author Ilia Gubarev
 */
public interface TemplateHelper<T> extends Helper<T> {

    /**
     * Gets the name of this helper.
     *
     * @return helper's name.
     */
    String getName();
}
