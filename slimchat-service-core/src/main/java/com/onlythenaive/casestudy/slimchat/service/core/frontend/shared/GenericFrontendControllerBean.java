package com.onlythenaive.casestudy.slimchat.service.core.frontend.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileFacade;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionDescriptor;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionDescriptorService;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;

public abstract class GenericFrontendControllerBean extends GenericComponentBean {

    @Autowired
    private ProfileFacade profileFacade;

    @Autowired
    private ExceptionDescriptorService exceptionDescriptorService;

    private List<Function<OperationException, ModelAndView>> handlers;

    protected String getUriPrefix() {
        return "/";
    }

    protected ModelAndView redirect(String uri) {
        return redirect(uri, null);
    }

    protected ModelAndView redirect(String uri, @Nullable Map<String, Object> parameters) {
        StringBuilder redirectViewName = new StringBuilder()
                .append("redirect:")
                .append(getUriPrefix())
                .append(uri);
        if (parameters != null && !parameters.isEmpty()) {
            redirectViewName.append("?");
            parameters.forEach((name, value) -> redirectViewName.append(name).append("&").append(value));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(redirectViewName.toString());
        return modelAndView;
    }

    protected ModelAndView render(String viewName) {
        return render(viewName, null);
    }

    protected ModelAndView render(String viewName, @Nullable Object data) {
        return render(viewName, data, null, HttpStatus.OK);
    }

    protected ModelAndView render(String viewName, @Nullable Object data, @Nullable Object error, HttpStatus status) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getViewNamePrefix() + viewName);
        modelAndView.addObject("context", getViewContext());
        if (data != null) {
            modelAndView.addObject("data", data);
        }
        if (error != null) {
            modelAndView.addObject("error", error);
        }
        modelAndView.setStatus(status);
        return modelAndView;
    }

    protected Map<String, Object> getViewContext() {
        Map<String, Object> context = new HashMap<>();
        context.put("base", getUriPrefix());
        if (isAuthenticated()) {
            Profile user = this.profileFacade.getById(principalId());
            context.put("user", user);
        }
        return context;
    }

    protected String getViewNamePrefix() {
        return "";
    }

    @ExceptionHandler
    protected ModelAndView interceptOperationException(OperationException exception) {
        return handleOperationalException(exception);
    }

    @ExceptionHandler
    protected ModelAndView interceptException(Exception exception) {
        return handleException(exception);
    }

    private ModelAndView handleOperationalException(OperationException exception) {
        for (int i = this.handlers.size() - 1; i >= 0; i--) {
            Function<OperationException, ModelAndView> handler = this.handlers.get(i);
            try {
                return handler.apply(exception);
            } catch (Exception e) {
                // NOTE: let another handler work
            }
        }
        return handleException(exception);
    }

    private ModelAndView handleException(Exception exception) {
//        logger().error("An unknown occurred", exception);
//        String errorViewName = getViewNamePrefix() + "error";
        return redirect("error");
    }

    protected void registerOperationExceptionHandler(Function<OperationException, ModelAndView> handler) {
        this.handlers.add(handler);
    }

    protected ExceptionDescriptor extractDescriptor(OperationException exception) {
        return this.exceptionDescriptorService.extract(exception);
    }

    @PostConstruct
    private void init() {
        this.handlers = new ArrayList<>();
    }
}
