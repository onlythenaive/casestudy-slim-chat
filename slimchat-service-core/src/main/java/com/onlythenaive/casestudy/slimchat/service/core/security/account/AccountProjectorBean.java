package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Security account projector implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class AccountProjectorBean extends GenericComponentBean implements AccountProjector {

    @Override
    public Account project(AccountEntity entity) {
        return Account.builder()
                .id(entity.getId())
                .loginKey(entity.getLoginKey())
                .loginSecretHash(entity.getLoginSecretHash())
                .roles(entity.getRoles())
                .createdAt(entity.getCreatedAt())
                .lastModifiedAt(entity.getLastModifiedAt())
                .build();
    }
}
