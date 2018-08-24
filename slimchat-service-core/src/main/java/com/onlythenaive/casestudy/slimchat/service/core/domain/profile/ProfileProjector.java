package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

/**
 * User profile projector.
 *
 * @author Ilia Gubarev
 */
public interface ProfileProjector {

    /**
     * Creates a user profile projection from its entity.
     *
     * @param entity a user profile entity to be projected.
     * @return the created projection.
     */
    Profile project(ProfileEntity entity);

    /**
     * Creates a brief user profile projection from its entity.
     *
     * @param entity a user profile entity to be projected.
     * @return the created projection.
     */
    Profile projectPreview(ProfileEntity entity);
}
