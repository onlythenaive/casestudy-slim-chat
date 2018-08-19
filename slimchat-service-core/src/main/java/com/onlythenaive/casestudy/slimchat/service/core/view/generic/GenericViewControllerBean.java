package com.onlythenaive.casestudy.slimchat.service.core.view.generic;

import org.springframework.web.servlet.ModelAndView;

/**
 * Generic view controller implementation.
 *
 * @author Ilia Gubarev
 */
public abstract class GenericViewControllerBean {

    /**
     * Creates a simple model-view based on specified view name.
     *
     * @param name view name.
     * @return model-view for response rendering.
     */
    protected ModelAndView view(String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(name);
        return modelAndView;
    }

    /**
     * Creates a model-view for the subsequent redirection.
     *
     * @param targetUrl the target URL for the redirect.
     * @return redirection model-view.
     */
    protected ModelAndView redirect(String targetUrl) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + targetUrl);
        return modelAndView;
    }
}
