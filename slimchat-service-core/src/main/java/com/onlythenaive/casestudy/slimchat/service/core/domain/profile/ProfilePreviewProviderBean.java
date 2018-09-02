package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * User profile preview provider implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfilePreviewProviderBean extends GenericComponentBean implements ProfilePreviewProvider {

    @Autowired
    private ProfileProjector profileProjector;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile getById(String id) {
        return this.profileRepository.findById(id).map(this::projectPreview).orElse(null);
    }

    @Override
    public Collection<Profile> getById(Collection<String> ids) {
        return this.profileRepository.findAllById(ids).stream().map(this::projectPreview).collect(Collectors.toList());
    }

    private Profile projectPreview(ProfileEntity entity) {
        return this.profileProjector.projectPreview(entity);
    }
}
