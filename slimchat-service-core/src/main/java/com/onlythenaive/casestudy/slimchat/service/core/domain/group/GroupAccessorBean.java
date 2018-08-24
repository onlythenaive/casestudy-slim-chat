package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainAccessorBean;

/**
 * Chat group accessor implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class GroupAccessorBean extends GenericDomainAccessorBean<GroupEntity> implements GroupAccessor {

    @Override
    public GroupEntity ensureAccess(AccessLevel level, GroupEntity subject) {
        switch (level) {
            case PREVIEW:
            case VIEW:
            case CONTRIBUTE:
                return ensureParticipation(subject);
            case EDIT:
            case MODERATE:
                return ensureModeration(subject);
            default:
                throw notSupported();
        }
    }

    private GroupEntity ensureParticipation(GroupEntity entity) {
        ensureInclusion(entity.getParticipantIds());
        return entity;
    }

    private GroupEntity ensureModeration(GroupEntity entity) {
        ensureInclusion(entity.getModeratorIds());
        return entity;
    }

    private void ensureInclusion(Collection<String> userIds) {
        if (!userIds.contains(authenticated().getId())) {
            throw insufficientPrivileges();
        }
    }

    @Override
    protected String entityName() {
        return "group";
    }
}
