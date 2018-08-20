package com.onlythenaive.casestudy.slimchat.service.core.security.account;

import org.springframework.stereotype.Service;

@Service
public class AccountProjectorBean implements AccountProjector {

    @Override
    public Account intoAccount(AccountEntity entity) {
        return Account.builder()
                .id(entity.getId())
                .name(entity.getName())
                .passwordHash(entity.getPasswordHash())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
