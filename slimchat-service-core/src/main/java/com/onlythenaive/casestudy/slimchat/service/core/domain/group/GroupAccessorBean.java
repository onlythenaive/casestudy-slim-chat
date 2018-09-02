package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.access.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericEntityAccessorBean;

/**
 * Chat group accessor implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class GroupAccessorBean extends GenericEntityAccessorBean<GroupEntity> implements GroupAccessor {

    @Override
    public AccessLevel allowedAccessLevel(GroupEntity entity) {
        if (entity.getModeratorIds().contains(principalId())) {
            return AccessLevel.MANAGE;
        }
        if (entity.getParticipantIds().contains(principalId())) {
            return AccessLevel.CONTRIBUTE;
        }
        return AccessLevel.BYPASS;
    }
}
