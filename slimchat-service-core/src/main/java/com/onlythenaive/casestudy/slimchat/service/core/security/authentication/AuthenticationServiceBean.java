package com.onlythenaive.casestudy.slimchat.service.core.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.Token;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.TokenService;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Authentication service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class AuthenticationServiceBean extends GenericComponentBean implements AuthenticationService {

    @Autowired
    private AuthenticationContextConfigurator authenticationContextConfigurator;

    @Autowired
    private TokenService tokenService;

    @Override
    public Authentication createCurrentAuthentication(Account account, Token token) {
        Authentication authentication = Authentication.builder()
                .account(account)
                .token(token)
                .build();
        this.authenticationContextConfigurator.setAuthentication(authentication);
        return authentication;
    }

    @Override
    public void removeCurrentAuthentication() {
        this.authenticationContextConfigurator.getAuthentication().ifPresent(authentication -> {
            Token token = authentication.getToken();
            this.tokenService.removeTokenById(token.getId());
            this.authenticationContextConfigurator.setAuthentication(null);
        });
    }
}
