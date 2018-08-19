package com.onlythenaive.casestudy.slimchat.service.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.generic.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountService;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.Authentication;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationContextConfigurator;
import com.onlythenaive.casestudy.slimchat.service.core.security.password.PasswordService;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.Token;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.TokenService;

/**
 * Security service.
 *
 * @author Ilia Gubarev
 */
@Service
public class SecurityServiceBean extends GenericComponentBean implements SecurityService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationContextConfigurator authenticationContextConfigurator;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private TokenService tokenService;

    @Override
    public void createAccount(SecurityCredentials credentials) {
        Account account = this.accountService.createAccount(credentials.getNickname(), credentials.getPassword());
        createNewAuthenticationAndToken(account);
    }

    @Override
    public void login(SecurityCredentials credentials) {
        Account account = this.accountService.findAccountByNickname(credentials.getNickname());
        if (account == null) {
            throw loginFailed();
        }
        boolean verified = this.passwordService.verify(credentials.getPassword(), account.getPasswordHash());
        if (!verified) {
            throw loginFailed();
        }
        createNewAuthenticationAndToken(account);
    }

    @Override
    public void logout() {
        removeCurrentAuthenticationAndToken();
    }

    private void createNewAuthenticationAndToken(Account account) {
        String nickname = account.getNickname();
        Token token = this.tokenService.createToken(nickname);
        Authentication authentication = Authentication.builder()
                .account(account)
                .token(token)
                .build();
        this.authenticationContextConfigurator.setAuthentication(authentication);
    }

    private void removeCurrentAuthenticationAndToken() {
        Authentication authentication = this.authenticationContextConfigurator.getAuthentication();
        if (authentication != null) {
            Token token = authentication.getToken();
            this.tokenService.removeTokenById(token.getId());
            this.authenticationContextConfigurator.setAuthentication(null);
        }
    }

    private RuntimeException loginFailed() {
        return new RuntimeException("Login attempt failed");
    }
}
