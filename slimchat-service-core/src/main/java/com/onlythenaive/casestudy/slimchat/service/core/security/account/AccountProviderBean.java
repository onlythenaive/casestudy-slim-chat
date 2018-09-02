package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Security account provider implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class AccountProviderBean extends GenericComponentBean implements AccountProvider {

    @Autowired
    private AccountProjector accountProjector;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<Account> findById(String id) {
        return this.accountRepository.findById(id).map(this::project);
    }

    @Override
    public Optional<Account> findByLoginKey(String loginKey) {
        return this.accountRepository.findByLoginKey(loginKey).map(this::project);
    }

    private Account project(AccountEntity entity) {
        return this.accountProjector.project(entity);
    }
}
