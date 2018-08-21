package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.security.password.PasswordService;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Security account service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class AccountServiceBean extends GenericComponentBean implements AccountService {

    @Autowired
    private AccountProjector accountProjector;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordService passwordService;

    @Override
    public Account createAccount(String name, String password) {
        ensureNameUniqueness(name);
        AccountEntity accountEntity = createNewAccountEntity(name, password);
        return project(accountEntity);
    }

    @Override
    public Optional<Account> findAccountById(String id) {
        return this.accountRepository.findById(id).map(this::project);
    }

    @Override
    public Optional<Account> findAccountByName(String name) {
        return this.findAccountEntityByName(name).map(this::project);
    }

    private void ensureNameUniqueness(String name) {
        findAccountEntityByName(name).ifPresent((accountEntity) -> {
            throw accountAlreadyExists(accountEntity.getName());
        });
    }

    private AccountEntity createNewAccountEntity(String name, String password) {
        AccountEntity accountEntity = AccountEntity.builder()
                .id(uuid())
                .name(normalizeName(name))
                .passwordHash(hashPassword(password))
                .createdAt(now())
                .build();
        this.accountRepository.insert(accountEntity);
        return accountEntity;
    }

    private Optional<AccountEntity> findAccountEntityByName(String name) {
        return this.accountRepository.findByName(normalizeName(name));
    }

    private String normalizeName(String name) {
        return name.toLowerCase();
    }

    private String hashPassword(String password) {
        return this.passwordService.hash(password);
    }

    private Account project(AccountEntity entity) {
        return this.accountProjector.intoAccount(entity);
    }

    private RuntimeException accountAlreadyExists(String name) {
        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        return OperationException.builder()
                .category(ExceptionCategory.LOGIC)
                .comment("Account already exists")
                .data(data)
                .textcode("x.logic.account.creation.account-already-exists")
                .build();
    }
}
