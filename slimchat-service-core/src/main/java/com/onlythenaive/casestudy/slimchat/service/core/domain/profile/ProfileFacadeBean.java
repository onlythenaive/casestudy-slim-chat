package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;

@Service
public class ProfileFacadeBean extends GenericDomainComponentBean implements ProfileFacade {

    @Autowired
    private ProfileProjector profileProjector;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile getProfileByAccountName(String accountName) {

        // NOTE: retrieve current authenticated user
        Account account = authenticated();

        // NOTE: retrieve the requested profile
        ProfileEntity entity = this.profileRepository.getByAccountName(accountName);
        if (entity == null) {
            throw profileNotFound();
        }

        // NOTE: check for viewing permission
        if (!entity.getId().equals(account.getId())) {
            throw profileViewRestricted();
        }

        // NOTE: return a projection of the profile
        return this.profileProjector.intoProfile(entity);
    }

    @Override
    public Profile getProfileById(String id) {

        // NOTE: retrieve current authenticated user
        Account account = authenticated();

        // NOTE: retrieve the requested profile
        ProfileEntity entity = this.profileRepository.getById(id);
        if (entity == null) {
            throw profileNotFound();
        }

        // NOTE: check for viewing permission
        if (!entity.getId().equals(account.getId())) {
            throw profileViewRestricted();
        }

        // NOTE: return a projection of the profile
        return this.profileProjector.intoProfile(entity);
    }

    @Override
    public Profile updateProfile(ProfileInvoice invoice) {

        // NOTE: retrieve current authenticated user
        Account account = authenticated();

        // NOTE: retrieve the requested profile
        ProfileEntity entity = this.profileRepository.getById(invoice.getId());
        if (entity == null) {
            throw profileNotFound();
        }

        // NOTE: update the profile with new data and persist it
        entity.setEmail(invoice.getEmail());
        entity.setFirstname(invoice.getFirstname());
        entity.setLastname(invoice.getLastname());
        entity.setStatus(invoice.getStatus());
        this.profileRepository.save(entity);

        // NOTE: return a projection of the profile
        return this.profileProjector.intoProfile(entity);
    }

    private OperationException profileNotFound() {
        throw OperationException.builder()
                .category(ExceptionCategory.LOGIC)
                .comment("Profile does not exist")
                .textcode("x.logic.profile.not-found")
                .build();
    }

    private OperationException profileViewRestricted() {
        throw OperationException.builder()
                .category(ExceptionCategory.LOGIC)
                .comment("Profile view restricted")
                .textcode("x.logic.profile.view-restricted")
                .build();
    }
}
