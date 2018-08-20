package com.onlythenaive.casestudy.slimchat.service.core.domain.shared;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.Authentication;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationContext;

public abstract class GenericDomainComponentBean {

    @Autowired
    private AuthenticationContext authenticationContext;

    protected Account authenticated() {
        Authentication authentication = this.authenticationContext.getAuthentication();
        if (authentication == null) {
            throw notAuthenticated();
        }
        return authentication.getAccount();
    }

    protected Instant now() {
        return Instant.now();
    }

    protected String uuid() {
        return UUID.randomUUID().toString();
    }

    private OperationException notAuthenticated() {
        throw OperationException.builder()
                .category(ExceptionCategory.SECURITY)
                .comment("Not authenticated")
                .textcode("x.security.not-authenticated")
                .build();
    }

}
