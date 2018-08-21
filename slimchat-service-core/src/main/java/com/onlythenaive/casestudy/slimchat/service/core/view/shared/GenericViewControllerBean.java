package com.onlythenaive.casestudy.slimchat.service.core.view.shared;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionDescriptor;
import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionDescriptorService;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.logging.LoggingService;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.Authentication;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationContext;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Generic view controller implementation.
 *
 * @author Ilia Gubarev
 */
public abstract class GenericViewControllerBean extends GenericComponentBean {

    @Autowired
    private AuthenticationContext authenticationContext;

    @Autowired
    private ExceptionDescriptorService exceptionDescriptorService;

    @Autowired
    private LoggingService loggingService;

    protected boolean isAuthenticated() {
        return this.authenticationContext.getAuthentication().isPresent();
    }

    protected Account authenticated() {
        return this.authenticationContext.getAuthentication().orElseThrow(this::notAuthenticated).getAccount();
    }

    protected ModelAndView defaultView() {
        return defaultView(null);
    }

    protected ModelAndView defaultView(Object data) {
        return defaultView(data, null, HttpStatus.OK);
    }

    protected ModelAndView defaultView(Object data, Object error, HttpStatus status) {
        return view(defaultViewName(), data, error, status);
    }

    protected ModelAndView view(String name) {
        return view(name, null);
    }

    protected ModelAndView view(String name, Object data) {
        return view(name, data, null, HttpStatus.OK);
    }

    protected ModelAndView view(String name, Object data, Object error, HttpStatus status) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(name);
        modelAndView.addObject("context", viewContext());
        modelAndView.addObject("data", data);
        modelAndView.addObject("error", error);
        modelAndView.setStatus(status);
        return modelAndView;
    }

    protected abstract String defaultViewName();

    protected Object viewContext() {
        Map<String, Object> context = new HashMap<>();
        context.put("version", "1.0.0");
        return context;
    }

    /**
     * Creates a model-view for the subsequent redirection.
     *
     * @param name the target view name for the redirect.
     * @return redirection model-view.
     */
    protected ModelAndView redirectToView(String name) {
        return redirectToView(name, null);
    }

    protected ModelAndView redirectToView(String name, Map<String, String> parameters) {
        String uri = "/view/" + name;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:" + uri);
        if (parameters != null) {
            modelAndView.addAllObjects(parameters);
        }
        logger().info("Redirect: " + uri);
        return modelAndView;
    }

    @ExceptionHandler
    protected ModelAndView handleOperationException(OperationException exception) {
        switch (exception.getCategory()) {
            case VALIDATION:
            case LOGIC:
                return badRequestError(exception);
            case SECURITY:
                return securityError(exception);
            case CONNECTIVITY:
                logError(exception);
                return connectivityError(exception);
            default:
                logError(exception);
                return unknownError(exception);
        }
    }

    @ExceptionHandler
    protected ModelAndView handleException(Exception exception) {
        logError(exception);
        return unknownError(exception);
    }

    private ModelAndView badRequestError(OperationException exception) {
        ExceptionDescriptor descriptor = extractExceptionDescriptor(exception);
        return defaultView(descriptor.getData(), descriptor, HttpStatus.BAD_REQUEST);
    }

    private ModelAndView connectivityError(OperationException exception) {
        ExceptionDescriptor descriptor = extractExceptionDescriptor(exception);
        return defaultView(descriptor.getData(), descriptor, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ModelAndView securityError(OperationException exception) {
        ExceptionDescriptor descriptor = extractExceptionDescriptor(exception);
        Map<String, String> parameters = new HashMap<>();
        parameters.put("reason", "unauthorized");
        parameters.put("message", descriptor.getMessageLocalized());
        return redirectToView("login", parameters);
    }

    private ModelAndView unknownError(Exception exception) {
        return redirectToView("error");
    }

    private ExceptionDescriptor extractExceptionDescriptor(OperationException exception) {
        return this.exceptionDescriptorService.extract(exception);
    }

    private void logError(Exception exception) {
        this.loggingService.error(exception);
    }

    private RuntimeException notAuthenticated() {
        return OperationException.builder()
                .category(ExceptionCategory.SECURITY)
                .comment("Not authenticated")
                .textcode("x.security.not-authenticated")
                .build();
    }
}
