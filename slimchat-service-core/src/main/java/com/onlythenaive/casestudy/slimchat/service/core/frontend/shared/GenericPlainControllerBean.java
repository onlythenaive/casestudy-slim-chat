package com.onlythenaive.casestudy.slimchat.service.core.frontend.shared;

import javax.annotation.PostConstruct;

import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;

public abstract class GenericPlainControllerBean extends GenericFrontendControllerBean {

    @Override
    protected String getUriPrefix() {
        String parentUriPrefix = super.getUriPrefix();
        return parentUriPrefix + "ui/plain/";
    }

    @Override
    protected String getViewNamePrefix() {
        return "plain/templates/";
    }

    @PostConstruct
    private void init() {
        registerOperationExceptionHandler(this::handleOperationException);
    }

    private ModelAndView handleOperationException(OperationException exception) {
        switch (exception.getCategory()) {
            case SECURITY:
                return redirect("login");
            default:
                throw exception;
        }
    }
}
