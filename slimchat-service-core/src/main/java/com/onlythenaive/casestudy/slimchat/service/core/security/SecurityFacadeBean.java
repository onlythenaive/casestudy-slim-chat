package com.onlythenaive.casestudy.slimchat.service.core.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountService;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationService;
import com.onlythenaive.casestudy.slimchat.service.core.security.password.PasswordHashService;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.Token;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.TokenService;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

@Service
public class SecurityFacadeBean extends GenericComponentBean implements SecurityFacade {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PasswordHashService passwordHashService;

    @Autowired
    private TokenService tokenService;

    @Override
    public void authenticateByAccountCredentials(String name, String password) {
        Account account = this.accountService.findAccountByName(name).orElseThrow(() -> loginFailed(name));
        boolean verified = this.passwordHashService.verify(password, account.getPasswordHash());
        if (!verified) {
            throw loginFailed(name);
        }
        Token token = this.tokenService.createToken(account.getId());
        this.authenticationService.createCurrentAuthentication(account, token);
    }

    @Override
    public void createAccount(String name, String password) {
        Account account = this.accountService.createAccount(name, password);
        Token token = this.tokenService.createToken(account.getId());
        this.authenticationService.createCurrentAuthentication(account, token);
    }

    @Override
    public void deauthenticate() {
        this.authenticationService.removeCurrentAuthentication();
    }

    private RuntimeException loginFailed(String accountName) {
        Map<String, String> data = new HashMap<>();
        data.put("accountName", accountName);
        return OperationException.builder()
                .category(ExceptionCategory.SECURITY)
                .textcode("x.security.authentication.login-failed")
                .comment("Login attempt failed")
                .data(data)
                .build();
    }
}
