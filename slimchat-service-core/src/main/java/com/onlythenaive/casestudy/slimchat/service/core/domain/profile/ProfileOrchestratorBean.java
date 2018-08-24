package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountActionAware;

/**
 * User profile orchestrator implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfileOrchestratorBean extends GenericDomainComponentBean implements AccountActionAware {

    @Autowired
    private ProfilePersister profilePersister;

    @Override
    public void onAccountCreated(Account account) {
        ProfileEntity entity = ProfileEntity.builder()
                .id(account.getId())
                .accountName(account.getName())
                .lastSpottedAt(now())
                .lastUpdatedAt(now())
                .registeredAt(now())
                .restricted(false)
                .connectedUserIds(new ArrayList<>())
                .status("Hey, I'm using Slim Chat now!")
                .build();
        this.profilePersister.insert(entity);
    }
}
