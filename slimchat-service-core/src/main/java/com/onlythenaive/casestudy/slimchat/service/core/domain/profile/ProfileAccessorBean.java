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
        if (subject.getId().equals(principalId())) {
            return AccessLevel.EDIT;
        }
        if (!subject.getRestricted() || subject.getConnectedProfileIds().contains(principalId())) {
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
}
