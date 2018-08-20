package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;

@Service
public class ProfileProjectorBean extends GenericDomainComponentBean implements ProfileProjector {

    @Override
    public Profile intoProfile(ProfileEntity entity) {
        return Profile.builder()
                .id(entity.getId())
                .accountName(entity.getAccountName())
                .email(entity.getEmail())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .lastSpottedAt(entity.getLastSpottedAt())
                .lastUpdatedAt(entity.getLastUpdatedAt())
                .registeredAt(entity.getRegisteredAt())
                .status(entity.getStatus())
                .own(entity.getId().equals(authenticated().getId()))
                .build();
    }
}
