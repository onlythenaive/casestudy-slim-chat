package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainPersisterBean;

/**
 * User profile persister implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfilePersisterBean extends DomainPersisterBean<ProfileEntity> implements ProfilePersister {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void insert(ProfileEntity entity) {
        if (entity.getId() == null) {
            entity.setId(uuid());
        }
        entity.setCreatedAt(now());
        entity.setLastModifiedAt(now());
        profileRepository.insert(entity);
    }

    @Override
    public void update(ProfileEntity entity) {
        entity.setLastModifiedAt(now());
        this.profileRepository.save(entity);
    }
}
