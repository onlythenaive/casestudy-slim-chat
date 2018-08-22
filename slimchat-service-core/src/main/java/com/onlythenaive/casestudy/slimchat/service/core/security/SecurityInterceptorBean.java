package com.onlythenaive.casestudy.slimchat.service.core.security;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationContext;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.http.HttpRequestWrapper;
import com.onlythenaive.casestudy.slimchat.service.core.utility.http.HttpResponseWrapper;

/**
 * Security interceptor implementation.
 *
 * @author Ilia Gubarev
 */
@Component
public class SecurityInterceptorBean extends GenericComponentBean implements SecurityInterceptor {

    @Autowired
    private AuthenticationContext authenticationContext;

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpRequestWrapper.from(request)
                .log()
                .securityTokenId()
                .ifPresent(this::authenticateByTokenIdIfPossible);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) {
        HttpResponseWrapper.from(response).securityTokenId(currentTokenId().orElse(null));
    }

    private void authenticateByTokenIdIfPossible(String tokenId) {
        try {
            this.securityService.authenticateByTokenId(tokenId);
        } catch (Exception e) {
            logger().debug("Authentication failed", e);
        }
    }

    private Optional<String> currentTokenId() {
        return this.authenticationContext.getAuthentication().map(auth -> auth.getToken().getId());
    }
}
