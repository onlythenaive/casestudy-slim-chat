package com.onlythenaive.casestudy.slimchat.service.core.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.Authentication;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationContextConfigurator;

@Component
public class SecurityInterceptorBean implements SecurityInterceptor {

    @Autowired
    private AuthenticationContextConfigurator authenticationContextConfigurator;

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("X-Security-Token")) {
                String tokenId = cookie.getValue();
                this.securityService.authenticateByToken(tokenId);
                break;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        Cookie cookie;
        Authentication authentication = this.authenticationContextConfigurator.getAuthentication();
        if (authentication != null) {
            cookie = new Cookie("X-Security-Token", authentication.getToken().getId());
        } else {
            cookie = new Cookie("X-Security-Token", null);
        }
        response.addCookie(cookie);
    }
}
