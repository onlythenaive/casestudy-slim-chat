package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Collection;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.EntityRepository;

/**
 * Chat group entity repository.
 *
 * @author Ilia Gubarev
 */
public interface GroupRepository extends EntityRepository<GroupEntity> {

    /**
     * Retrieves existing group entities by specified participant ID.
     *
     * @param participantId the ID of a participant.
     * @return the resulting list of groups.
     */
    Collection<GroupEntity> findByParticipantIds(String participantId);

    @Override
    default String getEntityName() {
        return GroupEntity.NAME;
    }
}
