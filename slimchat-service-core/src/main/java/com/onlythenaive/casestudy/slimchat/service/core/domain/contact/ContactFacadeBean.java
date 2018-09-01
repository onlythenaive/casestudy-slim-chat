package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileAccessor;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileEntity;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfilePersister;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProjector;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProvider;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.AccessLevel;

/**
 * Contact operations facade implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ContactFacadeBean extends GenericComponentBean implements ContactFacade {

    @Autowired(required = false)
    private Collection<ContactActionAware> contactActionHandlers;

    @Autowired
    private ProfileAccessor profileAccessor;

    @Autowired
    private ProfilePersister profilePersister;

    @Autowired
    private ProfileProjector profileProjector;

    @Autowired
    private ProfileProvider profileProvider;

    @Override
    public Collection<Profile> find() {
        return this.profileProvider.getAllConnected(principalId());
    }

    @Override
    public void remove(String profileId) {
        ProfileEntity actorEntity = this.profileAccessor.accessById(AccessLevel.BYPASS, principalId());
        ProfileEntity objectEntity = this.profileAccessor.accessById(AccessLevel.BYPASS, profileId);
        actorEntity.getConnectedProfileIds().remove(profileId);
        objectEntity.getConnectedProfileIds().remove(principalId());
        actorEntity = this.profilePersister.update(actorEntity);
        objectEntity = this.profilePersister.update(objectEntity);
        Profile actor = this.profileProjector.projectPreview(actorEntity);
        Profile object = this.profileProjector.projectPreview(objectEntity);
        handleAction(this.contactActionHandlers, handler -> handler.onContactDeleted(actor, object));
    }
}
