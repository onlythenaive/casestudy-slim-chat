package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.utility.hash.BcryptHashService;

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
    private BcryptHashService hashService;

    @Override
    public void create(AccountInvoice invoice) {
        ensureUniqueness(invoice);
        AccountEntity entity = accountEntityFromInvoice(invoice);
        AccountEntity insertedEntity = this.accountPersister.insert(entity);
        Account account = this.accountProjector.project(insertedEntity);
        handleAccountCreation(account, invoice);
   }

    private void ensureUniqueness(AccountInvoice invoice) {
        if (this.accountRepository.existsById(invoice.getId())) {
            throw accountAlreadyExists("accountId", invoice.getId());
        }
        if (this.accountRepository.existsByLoginKey(invoice.getLoginKey())) {
            throw accountAlreadyExists("accountLoginKey", invoice.getLoginKey());
        }
    }

    private AccountEntity accountEntityFromInvoice(AccountInvoice invoice) {
        return AccountEntity.builder()
                .id(invoice.getId())
                .loginKey(invoice.getLoginKey())
                .loginSecretHash(hashSecret(invoice.getLoginSecret()))
                .roles(new HashSet<>())
                .build();
    }

    private String hashSecret(String loginSecret) {
        return this.hashService.hash(loginSecret);
    }

    private void handleAccountCreation(Account account, AccountInvoice invoice) {
        AccountCreationEvent event = AccountCreationEvent.builder()
                .account(account)
                .email(invoice.getEmail())
                .firstname(invoice.getFirstname())
                .lastname(invoice.getLastname())
                .build();
        handleAction(this.accountActionHandlers, handler -> handler.onAccountCreated(event));
    }

    private OperationException accountAlreadyExists(String property, String value) {
        return OperationException.builder()
                .comment("Account already exists")
                .textcode("x.conflict.account.already-exists")
                .category(ExceptionCategory.CONFLICT)
                .dataAttribute(property, value)
                .build();
    }
}
