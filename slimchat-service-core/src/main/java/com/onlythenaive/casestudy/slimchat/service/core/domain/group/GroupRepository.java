package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Collection;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.EntityRepository;

/**
 * Chat group entity repository.
 *
 * @author Ilia Gubarev
 */
public interface GroupRepository extends EntityRepository<GroupEntity> {

    Collection<GroupEntity> findByParticipantIds(String participantId);
}
