package com.onlythenaive.casestudy.slimchat.service.core.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
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
    public void createAccount(String nickname, String password) {
        Account account = this.accountService.createAccount(nickname, password);
        createNewAuthenticationAndToken(account);
    }

    @Override
    public void login(String nickname, String password) {
        Account account = this.accountService.findAccountByNickname(nickname);
        if (account == null) {
            throw loginFailed(nickname);
        }
        boolean verified = this.passwordService.verify(password, account.getPasswordHash());
        if (!verified) {
            throw loginFailed(nickname);
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

    private RuntimeException loginFailed(String nickname) {
        Map<String, String> data = new HashMap<>();
        data.put("nickname", nickname);
        throw OperationException.builder()
                .category(ExceptionCategory.LOGIC)
                .comment("Login attempt failed")
                .data(data)
                .textcode("x.logic.security.login-failed")
                .build();
    }
}
