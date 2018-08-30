package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericAccessorBean;

/**
 * Chat group accessor implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class GroupAccessorBean extends GenericAccessorBean<GroupEntity> implements GroupAccessor {

    @Override
    public AccessLevel allowedAccessLevel(GroupEntity subject) {
        if (subject.getModeratorIds().contains(principalId())) {
            return AccessLevel.MANAGE;
        }
        if (subject.getParticipantIds().contains(principalId())) {
            return AccessLevel.CONTRIBUTE;
        }
        return AccessLevel.BYPASS;
    }

    @Override
    protected String getEntityName() {
        return "group";
    }
}
