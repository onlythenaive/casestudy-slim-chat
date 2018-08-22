package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;

@Service
public class ProfileProviderBean extends GenericDomainComponentBean implements ProfileProvider {

    @Autowired
    private ProfileProjector profileProjector;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile getProfile(String id) {
        return this.profileRepository.findById(id)
                .map(this::project)
                .orElseThrow(this::profileNotFound);
    }

    @Override
    public Optional<Profile> findProfile(String id) {
        return this.profileRepository.findById(id).map(this::project);
    }

    @Override
    public Collection<Profile> findProfiles(Collection<String> ids) {
        return this.profileRepository.findAllById(ids)
                .stream()
                .map(this::project)
                .collect(Collectors.toList());
    }

    private Profile project(ProfileEntity entity) {
        return this.profileProjector.intoProfile(entity);
    }

    private OperationException profileNotFound() {
        return OperationException.builder()
                .comment("Profile does not exist")
                .textcode("x.logic.profile.not-found")
                .category(ExceptionCategory.LOGIC)
                .build();
    }
}
