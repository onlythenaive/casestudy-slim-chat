package com.onlythenaive.casestudy.slimchat.service.core.domain.shared;

import org.springframework.beans.factory.annotation.Autowired;

import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationContext;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

public abstract class GenericDomainComponentBean extends GenericComponentBean {

    @Autowired
    private AuthenticationContext authenticationContext;

    protected Account authenticated() {
        return this.authenticationContext.getAuthentication().orElseThrow(this::notAuthenticated).getAccount();
    }

    private OperationException notAuthenticated() {
        throw OperationException.builder()
                .category(ExceptionCategory.SECURITY)
                .comment("Not authenticated")
                .textcode("x.security.not-authenticated")
                .build();
    }
}
