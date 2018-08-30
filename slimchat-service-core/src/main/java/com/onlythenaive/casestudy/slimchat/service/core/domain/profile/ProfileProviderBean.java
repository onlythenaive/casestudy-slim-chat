package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.AccessLevel;

/**
 * User profile provider implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfileProviderBean extends GenericComponentBean implements ProfileProvider {

    @Autowired
    private ProfileAccessor profileAccessor;

    @Autowired
    private ProfileProjector profileProjector;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Collection<Profile> getAll() {
        return this.profileRepository.findAll().stream()
                .map(this::projectPreview)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Profile> getAllConnected(String id) {
        ProfileEntity entity = this.profileAccessor.accessById(AccessLevel.VIEW, id);
        return this.profileRepository.findAllById(entity.getConnectedProfileIds()).stream()
                .map(this::projectPreview)
                .collect(Collectors.toList());
    }

    @Override
    public Profile getById(String id) {
        ProfileEntity entity = this.profileAccessor.accessById(id);
        boolean viewAllowed = this.profileAccessor.allowedAccessLevel(entity).greaterOrEqual(AccessLevel.VIEW);
        return viewAllowed ? project(entity) : projectPreview(entity);
    }

    @Override
    public Collection<Profile> getByIds(Collection<String> ids) {
        return this.profileRepository.findAllById(ids).stream()
                .map(this::projectPreview)
                .collect(Collectors.toList());
    }

    private Profile project(ProfileEntity entity) {
        return this.profileProjector.project(entity);
    }

    private Profile projectPreview(ProfileEntity entity) {
        return this.profileProjector.projectPreview(entity);
    }
}
