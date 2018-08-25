package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountEntity;
import com.onlythenaive.casestudy.slimchat.service.core.utility.password.PasswordHashService;

@BootstrapComponent
@DependsOn("mongoDataStorageBootstrapBean")
public class SecurityAccountBootstrapBean extends GenericEntityBootstrapBean<AccountEntity> {

    @Autowired
    private PasswordHashService passwordHashService;

    @Override
    protected String getBootstrapName() {
        return "security accounts";
    }

    @Override
    protected void executeBootstrap() {
        Collection<AccountEntity> entities = parseList("/bootstrap/dev/accounts.json", AccountEntity.class);
        entities.forEach(entity -> {
            entity.setPasswordHash(this.passwordHashService.hash("*"));
            insertBootstrappedEntity(entity);
        });
    }
}
