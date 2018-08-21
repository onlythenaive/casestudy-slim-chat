package com.onlythenaive.casestudy.slimchat.service.core.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.Authentication;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationContextConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SecurityInterceptorBean implements SecurityInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String ATTRIBUTE_SECURITY_TOKEN = "X-Security-Token";

    @Autowired
    private AuthenticationContextConfigurator authenticationContextConfigurator;

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.info("------------------------------------------------------------------------");
        logger.info("New request received: " + request.getMethod() + " " + request.getRequestURI());
        logCookies(request);
        String tokenIdFromRequest = discoverTokenIdIfAny(request);
        if (tokenIdFromRequest != null) {
            logger.info("Discovered token in request: " + tokenIdFromRequest);
            authenticateByTokenIdIfPossible(tokenIdFromRequest);
        } else {
            logger.info("No token discovered in request");
        }
        return true;
    }

    private void logCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            logger.info("Cookies are empty");
            return;
        }
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String path = cookie.getPath();
            String value = cookie.getValue();
            logger.info(String.format("Cookie found: name = %s, path = %s, value = %s, ", name, path, value));
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) {
        String tokenId = currentTokenIdIfAny();
        Cookie cookie = new Cookie(ATTRIBUTE_SECURITY_TOKEN, tokenId);
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/");
        response.addCookie(cookie);
        if (tokenId != null) {
            response.addHeader(ATTRIBUTE_SECURITY_TOKEN, tokenId);
        }
    }

    private void authenticateByTokenIdIfPossible(String tokenId) {
        try {
            this.securityService.authenticateByTokenId(tokenId);
        } catch (Exception e) {
            logAuthenticationFailure(e);
        }
    }

    @Nullable
    private String discoverTokenIdIfAny(HttpServletRequest request) {
        String tokenIdFromHeader = discoverTokenIdFromHeaderIfAny(request);
        if (tokenIdFromHeader != null) {
            return tokenIdFromHeader;
        }
        String tokenIdFromCookie = discoverTokenIdFromCookieIfAny(request);
        if (tokenIdFromCookie != null) {
            return tokenIdFromCookie;
        }
        return null;
    }

    @Nullable
    private String discoverTokenIdFromCookieIfAny(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(ATTRIBUTE_SECURITY_TOKEN)) {
                return normalizeTokenId(cookie.getValue());
            }
        }
        return null;
    }

    @Nullable
    private String discoverTokenIdFromHeaderIfAny(HttpServletRequest request) {
        String headerValue = request.getHeader(ATTRIBUTE_SECURITY_TOKEN);
        return normalizeTokenId(headerValue);
    }

    @Nullable
    private String normalizeTokenId(@Nullable String tokenId) {
        if (tokenId == null) {
            return null;
        }
        String normalizedTokenId = tokenId.trim();
        return normalizedTokenId.isEmpty() ? null : normalizedTokenId;
    }

    @Nullable
    private String currentTokenIdIfAny() {
        Authentication authentication = this.authenticationContextConfigurator.getAuthentication();
        return authentication != null ? authentication.getToken().getId() : null;
    }

    private void logAuthenticationFailure(Throwable throwable) {
        // TODO: log authentication failure
    }
}
