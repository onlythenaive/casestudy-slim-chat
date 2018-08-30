package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericAccessorBean;

/**
 * User profile accessor implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfileAccessorBean extends GenericAccessorBean<ProfileEntity> implements ProfileAccessor {

    @Override
    public AccessLevel allowedAccessLevel(ProfileEntity subject) {
        if (ownedByPrincipal(subject)) {
            return AccessLevel.EDIT;
        }
        if (!subject.getRestricted() || connectedWithPrincipal(subject)) {
            return AccessLevel.VIEW;
        }
        return AccessLevel.PREVIEW;
    }

    @Override
    protected AccessLevel getBypassThreshold() {
        return AccessLevel.PREVIEW;
    }

    @Override
    protected String getEntityName() {
        return "profile";
    }

    private boolean connectedWithPrincipal(ProfileEntity entity) {
        return entity.getConnectedProfileIds().contains(principalId());
    }

    private boolean ownedByPrincipal(ProfileEntity entity) {
        return entity.getId().equals(principalId());
    }
}
