package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainAccessorBean;

/**
 * Chat group accessor implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class GroupAccessorBean extends DomainAccessorBean<GroupEntity> implements GroupAccessor {

    @Override
    public GroupEntity ensureAccess(AccessLevel level, GroupEntity subject) {
        switch (level) {
            case BYPASS:
            case PREVIEW:
            case VIEW:
            case CONTRIBUTE:
                return ensureParticipation(subject);
            case EDIT:
            case MANAGE:
                return ensureModeration(subject);
            default:
                throw notSupported();
        }
    }

    @Override
    protected String getEntityName() {
        return "group";
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
        if (!userIds.contains(principalId())) {
            throw insufficientPrivileges();
        }
    }
}
