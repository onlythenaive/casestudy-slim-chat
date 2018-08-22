package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountActionAware;

/**
 * Profile service implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfileServiceBean extends GenericDomainComponentBean implements AccountActionAware {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void onAccountCreated(Account account) {
        ProfileEntity entity = ProfileEntity.builder()
                .id(account.getId())
                .accountName(account.getName())
                .lastSpottedAt(now())
                .lastUpdatedAt(now())
                .registeredAt(now())
                .build();
        this.profileRepository.insert(entity);
    }
}
