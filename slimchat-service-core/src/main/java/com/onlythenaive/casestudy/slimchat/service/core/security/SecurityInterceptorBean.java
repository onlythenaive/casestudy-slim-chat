package com.onlythenaive.casestudy.slimchat.service.core.security;

import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationContextConfigurator;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

@Component
public class SecurityInterceptorBean extends GenericComponentBean implements SecurityInterceptor {

    private static final String ATTRIBUTE_SECURITY_TOKEN = "X-Security-Token";

    @Autowired
    private AuthenticationContextConfigurator authenticationContextConfigurator;

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger().info("------------------------------------------------------------------------");
        logger().info("New request received: " + request.getMethod() + " " + request.getRequestURI());
        logCookies(request);
        Optional<String> tokenIdFromRequest = discoverTokenId(request);
        if (tokenIdFromRequest.isPresent()) {
            logger().info("Discovered token in request: " + tokenIdFromRequest);
            authenticateByTokenIdIfPossible(tokenIdFromRequest.get());
        } else {
            logger().info("No token discovered in request");
        }
        return true;
    }

    private void logCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            logger().info("Cookies are empty");
            return;
        }
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String path = cookie.getPath();
            String value = cookie.getValue();
            logger().info(String.format("Cookie found: name = %s, path = %s, value = %s, ", name, path, value));
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) {
        Optional<String> tokenId = currentTokenId();
        Cookie cookie = new Cookie(ATTRIBUTE_SECURITY_TOKEN, tokenId.orElse(null));
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/");
        response.addCookie(cookie);
        tokenId.ifPresent(id -> response.addHeader(ATTRIBUTE_SECURITY_TOKEN, id));
    }

    private void authenticateByTokenIdIfPossible(String tokenId) {
        try {
            this.securityService.authenticateByTokenId(tokenId);
        } catch (Exception e) {
            logAuthenticationFailure(e);
        }
    }

    private Optional<String> discoverTokenId(HttpServletRequest request) {
        Optional<String> tokenIdFromHeader = discoverTokenIdFromHeader(request);
        if (tokenIdFromHeader.isPresent()) {
            return tokenIdFromHeader;
        }
        return discoverTokenIdFromCookie(request);
    }

    private Optional<String> discoverTokenIdFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return Optional.empty();
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(ATTRIBUTE_SECURITY_TOKEN)) {
                return Optional.ofNullable(cookie.getValue()).map(this::normalizeTokenId);
            }
        }
        return Optional.empty();
    }

    private Optional<String> discoverTokenIdFromHeader(HttpServletRequest request) {
        String headerValue = request.getHeader(ATTRIBUTE_SECURITY_TOKEN);
        return Optional.ofNullable(headerValue).map(this::normalizeTokenId);
    }

    private String normalizeTokenId(String id) {
        String normalizedTokenId = id.trim();
        return normalizedTokenId.isEmpty() ? null : normalizedTokenId;
    }

    private Optional<String> currentTokenId() {
        return this.authenticationContextConfigurator.getAuthentication().map(a -> a.getToken().getId());
    }

    private void logAuthenticationFailure(Throwable throwable) {
        logger().info("Authentication failed due to occurred exception: " + throwable.getMessage());
    }
}
