package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.hash.Md5HashService;

/**
 * User profile projector implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfileProjectorBean extends GenericComponentBean implements ProfileProjector {

    @Autowired
    private Md5HashService hashService;

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
                .gravatarHash(gravatarHash(entity.getEmail()))
                .build();
    }

    @Override
    public Profile projectPreview(ProfileEntity entity) {
        return Profile.builder()
                .id(entity.getId())
                .accountName(entity.getAccountName())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .restricted(entity.getRestricted())
                .connected(connectedToPrincipal(entity))
                .ownedByPrincipal(ownedByPrincipal(entity))
                .gravatarHash(gravatarHash(entity.getEmail()))
                .build();
    }

    private String gravatarHash(String email) {
        return email != null ? this.hashService.hash(email) : "no-hash";
    }

    private boolean connectedToPrincipal(ProfileEntity entity) {
        Collection<String> connections = entity.getConnectedUserIds();
        return connections != null && connections.contains(principalId());
    }

    private boolean ownedByPrincipal(ProfileEntity entity) {
        return entity.getId().equals(principalId());
    }
}
