package com.onlythenaive.casestudy.slimchat.service.core.security.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountProvider;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.Token;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.TokenProvider;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.utility.http.HttpRequestWrapper;
import com.onlythenaive.casestudy.slimchat.service.core.utility.principal.Principal;
import com.onlythenaive.casestudy.slimchat.service.core.utility.principal.PrincipalContextConfigurator;

/**
 * Authentication interceptor.
 *
 * @author Ilia Gubarev
 */
@Component
public class AuthenticationInterceptorBean extends GenericComponentBean implements AuthenticationInterceptor {

    @Autowired
    private AccountProvider accountProvider;

    @Autowired
    private PrincipalContextConfigurator principalContextConfigurator;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpRequestWrapper.from(request)
                .log()
                .securityTokenId()
                .ifPresent(this::authenticateByTokenIdIfPossible);
        return true;
    }

    private void authenticateByTokenIdIfPossible(String tokenId) {
        try {
            authenticateByTokenId(tokenId);
        } catch (Exception e) {
            logger().debug("Authentication by token failed", e);
        }
    }

    private void authenticateByTokenId(String tokenId) {
        Token token = this.tokenProvider.findById(tokenId).orElseThrow(this::authenticationFailed);
        String accountId = token.getAccountId();
        Account account = this.accountProvider.findById(accountId).orElseThrow(this::authenticationFailed);
        Principal principal = principalFromAccount(account);
        this.principalContextConfigurator.setPrincipal(principal);
        logger().info("Authenticated with token " + tokenId);
    }

    private Principal principalFromAccount(Account account) {
        return Principal.builder()
                .id(account.getId())
                .name(account.getName())
                .build();
    }

    private OperationException authenticationFailed() {
        return OperationException.builder()
                .comment("Cannot authenticate by provided token")
                .textcode("x.security.authentication.invalid-token")
                .category(ExceptionCategory.SECURITY)
                .build();
    }
}
