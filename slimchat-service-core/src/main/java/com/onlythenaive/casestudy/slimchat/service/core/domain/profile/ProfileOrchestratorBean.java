package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountActionAware;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountCreationEvent;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * User profile orchestrator implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfileOrchestratorBean extends GenericComponentBean implements AccountActionAware {

    @Autowired
    private ProfilePersister profilePersister;

    @Override
    public void onAccountCreated(AccountCreationEvent event) {
        ProfileEntity entity = ProfileEntity.builder()
                .id(event.getAccount().getId())
                .firstname(event.getFirstname())
                .lastname(event.getLastname())
                .connectedProfileIds(new HashSet<>())
                .restricted(false)
                .status("Hey, I'm using Slim Chat now!")
                .lastSpottedAt(now())
                .build();
        this.profilePersister.insert(entity);
    }
}
