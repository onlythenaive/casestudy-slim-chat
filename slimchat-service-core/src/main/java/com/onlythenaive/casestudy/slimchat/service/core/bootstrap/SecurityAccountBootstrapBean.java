package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountEntity;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountRepository;
import com.onlythenaive.casestudy.slimchat.service.core.utility.password.PasswordHashService;

@BootstrapComponent
@DependsOn("mongoDataStorageBootstrapBean")
public class SecurityAccountBootstrapBean extends GenericBootstrapBean {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    protected String getBootstrapName() {
        return "security accounts";
    }

    @Override
    protected void executeBootstrap() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<AccountEntity>> typeReference = new TypeReference<List<AccountEntity>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/bootstrap/dev/accounts.json");
        try {
            List<AccountEntity> accounts = mapper.readValue(inputStream, typeReference);
            accounts.forEach(this::insertBootstrappedEntity);
        } catch (IOException e){
            System.out.println("Unable to save users: " + e.getMessage());
        }
    }

    @Autowired
    private PasswordHashService passwordHashService;

    private void insertBootstrappedEntity(AccountEntity entity) {
        if (entity.getId() == null) {
            entity.setId(uuid());
        }
        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(now());
        }
        if (entity.getPasswordHash() == null) {
            entity.setPasswordHash(this.passwordHashService.hash("*"));
        }
        this.accountRepository.save(entity);
    }
}
