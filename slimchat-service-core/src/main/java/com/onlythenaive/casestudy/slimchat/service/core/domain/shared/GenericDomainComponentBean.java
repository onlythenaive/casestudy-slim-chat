package com.onlythenaive.casestudy.slimchat.service.core.domain.shared;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationContext;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Generic domain component implementation.
 *
 * @author Ili Gubarev
 */
public abstract class GenericDomainComponentBean extends GenericComponentBean {

    @Autowired
    private AuthenticationContext authenticationContext;

    protected Account authenticated() {
        return this.authenticationContext.getAuthentication().orElseThrow(this::notAuthenticated).getAccount();
    }

    /**
     * Creates a new operational exception to thrown in case of insufficient principal privileges.
     *
     * @return the created operation exception.
     */
    protected RuntimeException insufficientPrivileges() {
        return OperationException.builder()
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
        return OperationException.builder()
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
        return OperationException.builder()
                .category(ExceptionCategory.UNKNOWN)
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
        return OperationException.builder()
                .comment(String.format("%s with %s = %s not found", entityName, property, value))
                .category(ExceptionCategory.LOGIC)
                .textcode("x.logic." + entityName + ".not-found")
                .data(data)
                .build();
    }
}
