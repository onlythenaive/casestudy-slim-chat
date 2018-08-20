package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class ProfileAccountServiceAdviceBean extends GenericDomainComponentBean {

    @Autowired
    private ProfileRepository profileRepository;

    @AfterReturning(pointcut = "execution(* createAccount(..))", returning = "result")
    public void handleAccountCreation(Object result) {

        // NOTE: retrieve created account
        if (!(result instanceof Account)) {
            return;
        }
        Account account = (Account) result;

        // NOTE: create a new profile
        ProfileEntity entity = ProfileEntity.builder()
                .id(account.getId())
                .accountName(account.getName())
                .lastSpottedAt(now())
                .lastUpdatedAt(now())
                .registeredAt(now())
                .build();

        // NOTE: persist the profile
        this.profileRepository.insert(entity);
    }
}
