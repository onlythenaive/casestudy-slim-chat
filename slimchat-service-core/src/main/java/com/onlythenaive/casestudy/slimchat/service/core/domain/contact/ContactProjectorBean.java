package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileProvider;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;

@Service
public class ContactProjectorBean extends GenericDomainComponentBean implements ContactProjector {

    @Autowired
    private ProfileProvider profileProvider;

    @Override
    public Contact intoContact(ContactEntity entity) {
        boolean own = entity.getInitiatorId().equals(principalId());
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
        return this.profileProvider.getById(participantId);
    }
}
