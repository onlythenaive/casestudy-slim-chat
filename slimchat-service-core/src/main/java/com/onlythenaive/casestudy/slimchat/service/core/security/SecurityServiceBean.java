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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Security service.
 *
 * @author Ilia Gubarev
 */
@Service
public class SecurityServiceBean extends GenericComponentBean implements SecurityService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationContextConfigurator authenticationContextConfigurator;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private TokenService tokenService;

    @Override
    public void authenticateByTokenId(String tokenId) {
        Token token = this.tokenService.findTokenById(tokenId);
        if (token == null) {
            throw authenticationByTokenFailed();
        }
        Account account = this.accountService.getAccountById(token.getAccountId());
        setupCurrentAuthentication(account, token);
        logger.info("Authenticated with token " + tokenId);
    }

    @Override
    public void createAccount(String name, String password) {
        Account account = this.accountService.createAccount(name, password);
        Token token = this.tokenService.createToken(account.getId());
        setupCurrentAuthentication(account, token);
    }

    @Override
    public void login(String name, String password) {
        Account account = this.accountService.getAccountByName(name);
        if (account == null) {
            throw loginFailed(name);
        }
        boolean verified = this.passwordService.verify(password, account.getPasswordHash());
        if (!verified) {
            throw loginFailed(name);
        }
        Token token = this.tokenService.createToken(account.getId());
        setupCurrentAuthentication(account, token);
    }

    @Override
    public void logout() {
        removeCurrentAuthenticationAndToken();
    }

    private void setupCurrentAuthentication(Account account, Token token) {
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

    private RuntimeException authenticationByTokenFailed() {
        return OperationException.builder()
                .category(ExceptionCategory.SECURITY)
                .textcode("x.security.authentication.invalid-token")
                .comment("Cannot authenticate by provided token")
                .build();
    }

    private RuntimeException loginFailed(String accountName) {
        Map<String, String> data = new HashMap<>();
        data.put("nickname", accountName);
        return OperationException.builder()
                .category(ExceptionCategory.SECURITY)
                .textcode("x.security.authentication.login-failed")
                .comment("Login attempt failed")
                .data(data)
                .build();
    }
}
