package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainComponentBean;

/**
 * User profile projector implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfileProjectorBean extends DomainComponentBean implements ProfileProjector {

    @Override
    public Profile project(ProfileEntity entity) {
        return Profile.builder()
                .id(entity.getId())
                .accountName(entity.getAccountName())
                .email(entity.getEmail())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .lastSpottedAt(entity.getLastSpottedAt())
                .lastUpdatedAt(entity.getLastModifiedAt())
                .registeredAt(entity.getRegisteredAt())
                .restricted(entity.getRestricted())
                .connected(connectedToPrincipal(entity))
                .status(entity.getStatus())
                .ownedByPrincipal(ownedByPrincipal(entity))
                .build();
    }

    @Override
    public Profile projectPreview(ProfileEntity entity) {
        return Profile.builder()
                .id(entity.getId())
                .accountName(entity.getAccountName())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .ownedByPrincipal(ownedByPrincipal(entity))
                .build();
    }

    private boolean connectedToPrincipal(ProfileEntity entity) {
        Collection<String> connections = entity.getConnectedUserIds();
        return connections != null && connections.contains(principalId());
    }

    private boolean ownedByPrincipal(ProfileEntity entity) {
        return entity.getId().equals(principalId());
    }
}
