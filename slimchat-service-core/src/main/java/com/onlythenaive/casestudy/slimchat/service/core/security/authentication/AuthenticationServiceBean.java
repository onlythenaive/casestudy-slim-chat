package com.onlythenaive.casestudy.slimchat.service.core.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.Token;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

@Service
public class AuthenticationServiceBean extends GenericComponentBean implements AuthenticationService {

    @Autowired
    private AuthenticationContextConfigurator authenticationContextConfigurator;

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
        this.authenticationContextConfigurator.setAuthentication(null);
    }
}
