package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileEntity;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProjector;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileRepository;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;

@Service
public class ContactProjectorBean extends GenericDomainComponentBean implements ContactProjector {

    @Autowired
    private ProfileProjector profileProjector;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Contact intoContact(ContactEntity entity) {
        boolean own = entity.getInitiatorId().equals(authenticated().getId());
        return Contact.builder()
                .id(entity.getId())
                .initiator(participant(entity.getInitiatorId()))
                .acceptor(participant(entity.getAcceptorId()))
                .introduction(entity.getIntroduction())
                .accepted(entity.isAccepted())
                .pending(!entity.isAccepted() && own)
                .requested(!entity.isAccepted() && !own)
                .own(own)
                .build();
    }

    private Profile participant(String participantId) {
        ProfileEntity profileEntity = this.profileRepository.getById(participantId);
        return this.profileProjector.intoProfile(profileEntity);
    }
}
