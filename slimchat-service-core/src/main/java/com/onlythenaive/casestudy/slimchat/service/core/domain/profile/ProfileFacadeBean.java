package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;

/**
 * Profile operations facade implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfileFacadeBean extends GenericDomainComponentBean implements ProfileFacade {

    @Autowired
    private ProfileAccessor profileAccessor;

    @Autowired
    private ProfilePersister profilePersister;

    @Autowired
    private ProfileProjector profileProjector;

    @Autowired
    private ProfileProvider profileProvider;

    @Override
    public Profile getByAccountName(String accountName) {
        return this.profileProvider.getByAccountName(accountName);
    }

    @Override
    public Profile getById(String id) {
        return this.profileProvider.getById(id);
    }

    @Override
    public Profile update(String id, ProfileUpdateInvoice invoice) {
        ProfileEntity entity = this.profileAccessor.accessById(AccessLevel.EDIT, id);
        updateEntityFromInvoice(entity, invoice);
        this.profilePersister.update(entity);
        return this.profileProjector.project(entity);
    }

    private void updateEntityFromInvoice(ProfileEntity entity, ProfileUpdateInvoice invoice) {
        entity.setEmail(invoice.getEmail());
        entity.setFirstname(invoice.getFirstname());
        entity.setLastname(invoice.getLastname());
        entity.setStatus(invoice.getStatus());
        entity.setRestricted(invoice.getRestricted());
    }
}
