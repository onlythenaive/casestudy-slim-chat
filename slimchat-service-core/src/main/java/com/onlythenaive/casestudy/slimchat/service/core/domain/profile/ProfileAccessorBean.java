package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.access.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericEntityAccessorBean;

/**
 * User profile accessor implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class ProfileAccessorBean extends GenericEntityAccessorBean<ProfileEntity> implements ProfileAccessor {

    @Override
    public AccessLevel allowedAccessLevel(ProfileEntity entity) {
        if (entity.getId().equals(principalId())) {
            return AccessLevel.EDIT;
        }
        if (!entity.getRestricted() || entity.getConnectedProfileIds().contains(principalId())) {
            return AccessLevel.VIEW;
        }
        return AccessLevel.PREVIEW;
    }

    @Override
    protected AccessLevel getBypassThreshold() {
        return AccessLevel.PREVIEW;
    }
}
