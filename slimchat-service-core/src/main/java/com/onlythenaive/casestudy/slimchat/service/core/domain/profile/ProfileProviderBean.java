package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.utility.exception.OperationException;

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
    public Collection<Profile> find() {
        return this.profileRepository.findAll().stream()
                .map(this.profileProjector::projectPreview)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Profile> findConnected(String id) {
        ProfileEntity entity = this.profileAccessor.accessById(AccessLevel.VIEW, id);
        return this.profileRepository.findAllById(entity.getConnectedUserIds()).stream()
                .map(this.profileProjector::projectPreview)
                .collect(Collectors.toList());
    }

    @Override
    public Profile getByAccountName(String accountName) {
        ProfileEntity entity = this.profileRepository.findByAccountName(accountName)
                .orElseThrow(() -> profileNotFoundByAccountName(accountName));
        this.profileAccessor.ensureAccess(AccessLevel.VIEW, entity);
        return project(entity);
    }

    @Override
    public Profile getById(String id) {
        ProfileEntity entity = this.profileAccessor.accessById(AccessLevel.VIEW, id);
        return project(entity);
    }

    @Override
    public Optional<Profile> findPreviewById(String id) {
        return this.profileAccessor.accessByIdIfAny(AccessLevel.PREVIEW, id).map(this::project);
    }

    @Override
    public Collection<Profile> findPreviews(Collection<String> ids) {
        return this.profileRepository.findAllById(ids).stream()
                .map(this.profileProjector::projectPreview)
                .collect(Collectors.toList());
    }

    private Profile project(ProfileEntity entity) {
        return this.profileProjector.project(entity);
    }

    private OperationException profileNotFoundByAccountName(String accountName) {
        return notFoundByProperty("profile", "accountName", accountName);
    }
}
