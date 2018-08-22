package com.onlythenaive.casestudy.slimchat.service.core.utility.http;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpResponseWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpResponseWrapper.class);

    public static HttpResponseWrapper from(HttpServletResponse response) {
        return new HttpResponseWrapper(response);
    }

    private final HttpServletResponse response;

    public HttpResponseWrapper(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public HttpResponseWrapper securityTokenId(String tokenId) {
        return headerAndCookie(HttpMetadata.SECURITY_TOKEN.getTextcode(), tokenId);
    }

    public HttpResponseWrapper cookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(Integer.MAX_VALUE);
        cookie.setPath("/");
        response.addCookie(cookie);
        return this;
    }

    public HttpResponseWrapper header(String name, String value) {
        response.addHeader(name, value);
        return this;
    }

    public HttpResponseWrapper headerAndCookie(String name, String value) {
        return cookie(name, value).header(name, value);
    }
}
