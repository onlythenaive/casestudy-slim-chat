package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileEntity;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfilePersister;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProjector;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfilePreviewProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileRepository;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

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
    private ProfilePersister profilePersister;

    @Autowired
    private ProfileProjector profileProjector;

    @Autowired
    private ProfilePreviewProvider profileProvider;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Collection<Profile> find() {
        ProfileEntity entity = this.profileRepository.findById(principalId()).orElseThrow(RuntimeException::new);
        Collection<String> connectedIds = entity.getConnectedProfileIds();
        return this.profileProvider.getById(connectedIds);
    }

    @Override
    public void remove(String profileId) {
        ProfileEntity actorEntity = this.profileRepository.getById(principalId());
        ProfileEntity objectEntity = this.profileRepository.getById(profileId);
        actorEntity.getConnectedProfileIds().remove(profileId);
        objectEntity.getConnectedProfileIds().remove(principalId());
        actorEntity = this.profilePersister.update(actorEntity);
        objectEntity = this.profilePersister.update(objectEntity);
        Profile actor = this.profileProjector.projectPreview(actorEntity);
        Profile object = this.profileProjector.projectPreview(objectEntity);
        handleAction(this.contactActionHandlers, handler -> handler.onContactDeleted(actor, object));
    }
}
