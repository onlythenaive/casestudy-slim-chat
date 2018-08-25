package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.utility.password.PasswordHashService;

/**
 * Security account facade implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class AccountFacadeBean extends GenericComponentBean implements AccountFacade {

    @Autowired(required = false)
    private Collection<AccountActionAware> accountActionHandlers;

    @Autowired
    private AccountPersister accountPersister;

    @Autowired
    private AccountProjector accountProjector;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordHashService passwordHashService;

    @Override
    public void create(String name, String password) {
        ensureNameUniqueness(name);
        AccountEntity entity = accountEntity(name, password);
        this.accountPersister.insert(entity);
        Account account = this.accountProjector.project(entity);
        handleAction(this.accountActionHandlers, handler -> handler.onAccountCreated(account));
   }

    private void ensureNameUniqueness(String name) {
        if (this.accountRepository.existsByName(name)) {
            throw accountAlreadyExists(name);
        }
    }

    private AccountEntity accountEntity(String name, String password) {
        return AccountEntity.builder()
                .id(uuid())
                .name(name)
                .passwordHash(hashPassword(password))
                .createdAt(now())
                .build();
    }

    private String hashPassword(String password) {
        return this.passwordHashService.hash(password);
    }

    private OperationException accountAlreadyExists(String name) {
        return OperationException.builder()
                .comment("Account already exists")
                .textcode("x.conflict.account.already-exists")
                .category(ExceptionCategory.CONFLICT)
                .dataAttribute("accountName", name)
                .build();
    }
}
