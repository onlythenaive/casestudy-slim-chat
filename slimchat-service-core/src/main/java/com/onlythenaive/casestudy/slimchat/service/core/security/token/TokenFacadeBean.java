package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountProvider;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.utility.password.PasswordHashService;

/**
 * Security token operations facade implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class TokenFacadeBean extends GenericComponentBean implements TokenFacade {

    @Autowired
    private AccountProvider accountProvider;

    @Autowired
    private PasswordHashService passwordHashService;

    @Autowired
    private TokenContextConfigurator tokenContextConfigurator;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public void createFromAccountCredentials(String name, String password) {
        Account account = this.accountProvider.findByName(name).orElseThrow(() -> loginFailed(name));
        boolean verified = this.passwordHashService.verify(password, account.getPasswordHash());
        if (!verified) {
            throw loginFailed(name);
        }
        this.tokenGenerator.generateForAccountId(account.getId());
    }

    @Override
    public void deleteById(String id) {
        this.tokenProvider.findById(id).ifPresent(token -> {
            this.tokenRepository.deleteById(id);
            this.tokenContextConfigurator.setProvided(null);
            logger().debug("Deleted a security token with ID = {}", token.getId());
        });
    }

    private OperationException loginFailed(String accountName) {
        return OperationException.builder()
                .comment("Login attempt failed")
                .textcode("x.security.authentication.login-failed")
                .category(ExceptionCategory.SECURITY)
                .dataAttribute("accountName", accountName)
                .build();
    }
}
