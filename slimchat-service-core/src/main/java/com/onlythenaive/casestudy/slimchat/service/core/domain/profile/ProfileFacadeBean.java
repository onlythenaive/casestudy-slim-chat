package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.access.AccessLevel;

/**
 * Profile operations facade implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfileFacadeBean extends GenericComponentBean implements ProfileFacade {

    @Autowired
    private ProfileAccessor profileAccessor;

    @Autowired
    private ProfilePersister profilePersister;

    @Autowired
    private ProfileProjector profileProjector;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile getById(String id) {
        ProfileEntity entity = this.profileRepository.getById(id);
        AccessLevel level = this.profileAccessor.allowedAccessLevel(entity);
        return level.greaterOrEqual(AccessLevel.VIEW) ? project(entity) : projectPreview(entity);
    }

    @Override
    public ProfileSearchResult getSearchResult(ProfileSearchInvoice searchInvoice) {
        Pageable paging = paging(searchInvoice);
        Example<ProfileEntity> entityExample = entityExample(searchInvoice);
        Page<ProfileEntity> entityPage = this.profileRepository.findAll(entityExample, paging);
        return searchResult(entityPage);
    }

    @Override
    public Profile update(String id, ProfileUpdateInvoice invoice) {
        ProfileEntity entity = this.profileRepository.getById(id);
        this.profileAccessor.ensureAccess(entity, AccessLevel.EDIT);
        applyInvoiceProperties(entity, invoice);
        ProfileEntity updatedEntity = this.profilePersister.update(entity);
        return project(updatedEntity);
    }

    private Example<ProfileEntity> entityExample(ProfileSearchInvoice searchInvoice) {
        // TODO: construct the real entity example
        ProfileEntity probe = ProfileEntity.builder().build();
        return Example.of(probe);
    }

    private Pageable paging(ProfileSearchInvoice searchInvoice) {
        // TODO: construct the real page request
        return Pageable.unpaged();
    }

    private ProfileSearchResult searchResult(Page<ProfileEntity> page) {
        return ProfileSearchResult.builder()
                .items(projectPreviews(page.getContent()))
                .total(page.getTotalElements())
                .build();
    }

    private void applyInvoiceProperties(ProfileEntity entity, ProfileUpdateInvoice invoice) {
        entity.setEmail(invoice.getEmail());
        entity.setFirstname(invoice.getFirstname());
        entity.setLastname(invoice.getLastname());
        entity.setStatus(invoice.getStatus());
        entity.setRestricted(invoice.getRestricted());
    }

    private Profile project(ProfileEntity entity) {
        return this.profileProjector.project(entity);
    }

    private Profile projectPreview(ProfileEntity entity) {
        return this.profileProjector.projectPreview(entity);
    }

    private Collection<Profile> projectPreviews(Collection<ProfileEntity> entities) {
        return entities.stream().map(this::projectPreview).collect(Collectors.toList());
    }
}
