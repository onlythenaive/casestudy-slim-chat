package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Collection;

public interface GroupFacade {

    Group create(GroupInvoice invoice);

    Collection<Group> findByCaptionTemplate(String captionTemplate);

    Group getById(String id);

    void inviteParticipant(String id, String participantId);

    void promoteParticipant(String id, String participantId);

    void kickParticipant(String id, String participantId);

    void leave(String id);

    Group updateCaption(String id, String caption);
}
