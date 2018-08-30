package com.onlythenaive.casestudy.slimchat.service.core.security.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountProvider;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.Token;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.TokenContextConfigurator;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.TokenProvider;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.utility.http.HttpRequestWrapper;
import com.onlythenaive.casestudy.slimchat.service.core.utility.http.HttpResponseWrapper;
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
    private TokenContextConfigurator tokenContextConfigurator;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger().debug("Request received: {} {}", request.getMethod(), request.getRequestURI());
        if (shouldSkip(request)) {
            logger().debug("Authentication skipped");
            return true;
        }
        HttpRequestWrapper.from(request)
                .log()
                .securityTokenId()
                .ifPresent(this::authenticateByTokenIdIfPossible);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) {
        if (shouldSkip(request)) {
            return;
        }
        provideTokenIdWithResponse(response);
    }

    private void authenticateByTokenIdIfPossible(String tokenId) {
        try {
            authenticateByTokenId(tokenId);
        } catch (Exception e) {
            logger().trace("Authentication by token failed", e);
        }
    }

    private void authenticateByTokenId(String tokenId) {
        Token token = this.tokenProvider.findById(tokenId).orElseThrow(this::authenticationFailed);
        String accountId = token.getAccountId();
        Account account = this.accountProvider.findById(accountId).orElseThrow(this::authenticationFailed);
        Principal principal = principalFromAccount(account);
        this.principalContextConfigurator.setPrincipal(principal);
        this.tokenContextConfigurator.setProvided(token);
        logger().info("Authenticated with token " + tokenId);
    }

    private Principal principalFromAccount(Account account) {
        return Principal.builder()
                .id(account.getId())
                .roles(account.getRoles())
                .build();
    }

    private void provideTokenIdWithResponse(HttpServletResponse response) {
        String tokenId = null;
        Token generatedToken = this.tokenContextConfigurator.getGenerated().orElse(null);
        Token providedToken = this.tokenContextConfigurator.getProvided().orElse(null);
        if (providedToken != null) {
            tokenId = providedToken.getId();
        } else if (generatedToken != null) {
            tokenId = generatedToken.getId();
        }
        HttpResponseWrapper.from(response).securityTokenId(tokenId);
    }

    private OperationException authenticationFailed() {
        return OperationException.builder()
                .comment("Cannot authenticate by provided token")
                .textcode("x.security.authentication.invalid-token")
                .category(ExceptionCategory.SECURITY)
                .build();
    }

    private boolean shouldSkip(HttpServletRequest request) {
        if (!request.getMethod().equals("GET")) {
            return false;
        }
        String uri = request.getRequestURI();
        return uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith(".png");
    }
}
