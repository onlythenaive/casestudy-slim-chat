package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileRepository;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.AccountActionAware;

@Service
public class ContactServiceBean extends GenericDomainComponentBean implements AccountActionAware {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void onAccountCreated(Account account) {
        this.profileRepository.findAll().stream()
                .filter(p -> !p.getId().equals(account.getId()))
                .forEach(acceptor -> {
                    ContactEntity entity = ContactEntity.builder()
                            .id(uuid())
                            .initiatorId(account.getId())
                            .acceptorId(acceptor.getId())
                            .introduction("Hi, I'm now using Slim Chat!")
                            .createdAt(now())
                            .build();
                    this.contactRepository.insert(entity);
                });
    }
}
