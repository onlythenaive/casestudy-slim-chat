package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Collection;
import java.util.Optional;

/**
 * User profile provider.
 *
 * @author Ilia Gubarev
 */
public interface ProfileProvider {

    /**
     * Finds all existing profiles.
     *
     * @return a collection of existing profiles.
     */
    Collection<Profile> getAll();

    /**
     * Finds all connected profiles for a specified profile by its ID.
     *
     * @param id the ID of specified profile.
     * @return the resulting collection of profiles.
     */
    Collection<Profile> getAllConnected(String id);

    /**
     * Provides an existing user profile by its ID.
     *
     * @param id the ID of a user profile.
     * @return the requested profile.
     */
    Profile getById(String id);

    /**
     * Provides a collections of existing user profile previews by their IDs.
     *
     * @param ids the IDs of profiles.
     * @return a collection of requested profiles.
     */
    Collection<Profile> getByIds(Collection<String> ids);
}
