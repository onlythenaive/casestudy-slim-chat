package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountEntity;
import com.onlythenaive.casestudy.slimchat.service.core.utility.hash.BcryptHashService;

@BootstrapComponent
@DependsOn("mongoDataStorageBootstrapBean")
public class SecurityAccountBootstrapBean extends GenericEntityBootstrapBean<AccountEntity> {

    @Autowired
    private BcryptHashService hashService;

    @Override
    protected String getBootstrapName() {
        return "security accounts";
    }

    @Override
    protected void executeBootstrap() {
        Collection<AccountEntity> entities = parseList("/bootstrap/dev/accounts.json", AccountEntity.class);
        entities.forEach(entity -> {
            entity.setSecretHash(this.hashService.hash("*"));
            entity.setRoles(new HashSet<>());
            insertBootstrappedEntity(entity);
        });
    }
}
