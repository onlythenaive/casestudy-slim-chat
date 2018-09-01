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
        ensureIdUniqueness(invoice.getId());
        AccountEntity entity = accountEntityFromInvoice(invoice);
        entity = this.accountPersister.insert(entity);
        handleAccountCreation(entity, invoice);
   }

    private void ensureIdUniqueness(String id) {
        if (this.accountRepository.existsById(id)) {
            throw accountAlreadyExists(id);
        }
    }

    private AccountEntity accountEntityFromInvoice(AccountInvoice invoice) {
        return AccountEntity.builder()
                .id(invoice.getId())
                .roles(new HashSet<>())
                .secretHash(hashSecret(invoice.getSecret()))
                .build();
    }

    private String hashSecret(String password) {
        return this.hashService.hash(password);
    }

    private void handleAccountCreation(AccountEntity entity, AccountInvoice invoice) {
        Account account = this.accountProjector.project(entity);
        AccountCreationEvent event = AccountCreationEvent.builder()
                .account(account)
                .email(invoice.getEmail())
                .firstname(invoice.getFirstname())
                .lastname(invoice.getLastname())
                .build();
        handleAction(this.accountActionHandlers, handler -> handler.onAccountCreated(event));
    }

    private OperationException accountAlreadyExists(String id) {
        return OperationException.builder()
                .comment("Account already exists")
                .textcode("x.conflict.account.already-exists")
                .category(ExceptionCategory.CONFLICT)
                .dataAttribute("accountId", id)
                .build();
    }
}
