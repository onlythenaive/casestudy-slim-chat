package com.onlythenaive.casestudy.slimchat.service.core.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountActionAware;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountCreationEvent;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Security token orchestrator implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class TokenOrchestratorBean extends GenericComponentBean implements AccountActionAware {

    @Autowired
    private TokenGenerator tokenGenerator;

    @Override
    public void onAccountCreated(AccountCreationEvent event) {
        this.tokenGenerator.generateForAccountId(event.getAccount().getId());
    }
}
