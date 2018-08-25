package com.onlythenaive.casestudy.slimchat.service.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountService;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationService;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.Token;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.TokenService;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Security service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class SecurityServiceBean extends GenericComponentBean implements SecurityService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @Override
    public void authenticateByTokenId(String tokenId) {
        Token token = this.tokenService.findTokenById(tokenId).orElseThrow(this::authenticationFailed);
        String accountId = token.getAccountId();
        Account account = this.accountService.findAccountById(accountId).orElseThrow(this::authenticationFailed);
        this.authenticationService.createCurrentAuthentication(account, token);
        logger().info("Authenticated with token " + tokenId);
    }

    private RuntimeException authenticationFailed() {
        return OperationException.builder()
                .category(ExceptionCategory.SECURITY)
                .textcode("x.security.authentication.invalid-token")
                .comment("Cannot authenticate by provided token")
                .build();
    }
}
