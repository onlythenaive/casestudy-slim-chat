package com.onlythenaive.casestudy.slimchat.service.core.utility.http;

import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequestWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestWrapper.class);

    public static HttpRequestWrapper from(HttpServletRequest request) {
        return new HttpRequestWrapper(request);
    }

    private final HttpServletRequest request;

    public HttpRequestWrapper(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpRequestWrapper log() {
        logCookies();
        return this;
    }

    public Optional<String> securityTokenId() {
        return headerOrCookie(HttpMetadata.SECURITY_TOKEN.getTextcode());
    }

    public Optional<String> cookie(String name) {
        Cookie[] cookies = this.request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return Optional.empty();
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return Optional.ofNullable(cookie.getValue()).map(this::normalizeValue);
            }
        }
        return Optional.empty();
    }

    public Optional<String> header(String name) {
        String headerValue = this.request.getHeader(name);
        return Optional.ofNullable(headerValue).map(this::normalizeValue);
    }

    public Optional<String> headerOrCookie(String name) {
        Optional<String> valueFromHeader = header(name);
        if (valueFromHeader.isPresent()) {
            return valueFromHeader;
        }
        return cookie(name);
    }

    private String normalizeValue(String value) {
        String normalizedValue = value.trim();
        return normalizedValue.isEmpty() ? null : normalizedValue;
    }

    private void logCookies() {
        if (!LOGGER.isDebugEnabled()) {
            return;
        }
        Cookie[] cookies = this.request.getCookies();
        if (cookies == null || cookies.length == 0) {
            LOGGER.debug("Cookies are empty");
            return;
        }
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String path = cookie.getPath();
            String value = cookie.getValue();
            LOGGER.debug(String.format("Cookie found: name = %s, path = %s, value = %s, ", name, path, value));
        }
    }
}
