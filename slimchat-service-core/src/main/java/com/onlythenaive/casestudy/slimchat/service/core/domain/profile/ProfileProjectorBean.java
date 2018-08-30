package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

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
                .email(entity.getEmail())
                .emailHash(emailHash(entity))
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .status(entity.getStatus())
                .connected(connected(entity))
                .restricted(entity.getRestricted())
                .online(online(entity))
                .own(own(entity))
                .createdAt(entity.getCreatedAt())
                .lastModifiedAt(entity.getLastModifiedAt())
                .lastSpottedAt(entity.getLastSpottedAt())
                .preview(false)
                .build();
    }

    @Override
    public Profile projectPreview(ProfileEntity entity) {
        return Profile.builder()
                .id(entity.getId())
                .emailHash(emailHash(entity))
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .connected(connected(entity))
                .restricted(entity.getRestricted())
                .own(own(entity))
                .preview(true)
                .build();
    }

    private String emailHash(ProfileEntity entity) {
        if (entity.getEmail() == null) {
            return null;
        }
        return this.hashService.hash(entity.getEmail());
    }

    private boolean connected(ProfileEntity entity) {
        return entity.getConnectedProfileIds().contains(principalId());
    }

    private boolean online(ProfileEntity entity) {
        return entity.getLastSpottedAt() != null && entity.getLastSpottedAt().plusSeconds(120).isAfter(now());
    }

    private boolean own(ProfileEntity entity) {
        return entity.getId().equals(principalId());
    }
}
