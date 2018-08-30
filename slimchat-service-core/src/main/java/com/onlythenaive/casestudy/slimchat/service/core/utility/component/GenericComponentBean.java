package com.onlythenaive.casestudy.slimchat.service.core.utility.component;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.utility.principal.Principal;
import com.onlythenaive.casestudy.slimchat.service.core.utility.principal.PrincipalContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generic implementation of a component.
 *
 * @author Ilia Gubarev
 */
public abstract class GenericComponentBean {

    @Autowired
    private PrincipalContext principalContext;

    private Logger logger;

    protected OperationException.Builder exceptionBuilder() {
        return OperationException.builder();
    }

    /**
     * Provides a current logger.
     *
     * @return current logger.
     */
    protected Logger logger() {
        return this.logger;
    }

    /**
     * Provides current date and time.
     *
     * @return current date and time.
     */
    protected Instant now() {
        return Instant.now();
    }

    /**
     * Provides a new unique UUID.
     *
     * @return a new UUID.
     */
    protected String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * Creates a runtime exception that wraps a specified exception.
     *
     * @param exception an exception that needs to be wrapped.
     * @return runtime exception.
     */
    protected RuntimeException wrappedException(Exception exception) {
        return new RuntimeException(exception);
    }

    /**
     * Creates a new operational exception to thrown in case of insufficient principal privileges.
     *
     * @return the created operation exception.
     */
    protected RuntimeException insufficientPrivileges() {
        return exceptionBuilder()
                .category(ExceptionCategory.SECURITY)
                .textcode("x.security.insufficient-privileges")
                .comment("Not enough privileges for the requested operation")
                .build();
    }

    /**
     * Creates a new operational exception to thrown in case of absence of authentication.
     *
     * @return the created operation exception.
     */
    protected OperationException notAuthenticated() {
        return exceptionBuilder()
                .category(ExceptionCategory.SECURITY)
                .comment("Not authenticated")
                .textcode("x.security.not-authenticated")
                .build();
    }

    /**
     * Creates a new operational exception to thrown in case of not yet supported function invocation.
     *
     * @return the created operation exception.
     */
    protected OperationException notSupported() {
        return exceptionBuilder()
                .category(ExceptionCategory.UNPREDICTED)
                .comment("Not supported")
                .textcode("x.not-supported")
                .build();
    }

    /**
     * Creates a new operational exception to thrown in case of non-found resource.
     *
     * @param entityName the name of a domain entity.
     * @param id the ID of an entity.
     * @return the created operation exception.
     */
    protected OperationException notFoundById(String entityName, String id) {
        return notFoundByProperty(entityName, "ID", id);
    }

    /**
     * Creates a new operational exception to thrown in case of non-found resource.
     *
     * @param entityName the name of a domain entity.
     * @param property key property name.
     * @param value key property value.
     * @return the created operation exception.
     */
    protected OperationException notFoundByProperty(String entityName, String property, String value) {
        Map<String, Object> data = new HashMap<>();
        data.put(property, value);
        return exceptionBuilder()
                .comment(String.format("%s with %s = %s not found", entityName, property, value))
                .category(ExceptionCategory.CONFLICT)
                .textcode("x.logic." + entityName + ".not-found")
                .data(data)
                .build();
    }

    protected <H> void handleAction(Collection<H> actionHandlers, Consumer<H> handlerProcessor) {
        if (actionHandlers != null) {
            actionHandlers.forEach(handlerProcessor);
        }
    }

    protected boolean isAuthenticated() {
        return this.principalContext.getPrincipal().isPresent();
    }

    protected Principal principal() {
        return this.principalContext.getPrincipal().orElseThrow(this::notAuthenticated);
    }

    protected String principalId() {
        return principal().getId();
    }

    protected boolean principalGrantedWith(String role) {
        return principal().getRoles().contains(role);
    }

    @PostConstruct
    private void init() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }
}
