package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import java.util.HashMap;
import java.util.Map;

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
        AccountEntity existingEntity = retrieveAccount(name);
        if (existingEntity != null) {
            Map<String, String> data = new HashMap<>();
            data.put("name", name);
            throw OperationException.builder()
                    .category(ExceptionCategory.LOGIC)
                    .comment("Account already exists")
                    .data(data)
                    .textcode("x.logic.account.creation.account-already-exists")
                    .build();
        }
        AccountEntity entity = createNewAccount(name, password);
        return this.accountProjector.intoAccount(entity);
    }

    @Override
    public Account getAccountById(String id) {
        AccountEntity entity = this.accountRepository.getById(id);
        return this.accountProjector.intoAccount(entity);
    }

    @Override
    public Account getAccountByName(String name) {
        AccountEntity entity = retrieveAccount(name);
        return this.accountProjector.intoAccount(entity);
    }

    private AccountEntity createNewAccount(String name, String password) {
        AccountEntity entity = AccountEntity.builder()
                .id(uuid())
                .name(normalizedName(name))
                .passwordHash(passwordHash(password))
                .createdAt(now())
                .build();
        this.accountRepository.insert(entity);
        return entity;
    }

    private AccountEntity retrieveAccount(String name) {
        return this.accountRepository.getByName(normalizedName(name));
    }

    private String normalizedName(String name) {
        return name.toLowerCase();
    }

    private String passwordHash(String password) {
        return this.passwordService.hash(password);
    }
}
