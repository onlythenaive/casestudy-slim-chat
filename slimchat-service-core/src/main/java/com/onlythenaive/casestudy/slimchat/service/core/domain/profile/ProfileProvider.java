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
     * Provides an existing user profile by its referenced account name.
     *
     * @param accountName the referenced account name.
     * @return the requested profile.
     */
    Profile getByAccountName(String accountName);

    /**
     * Provides an existing user profile by its ID.
     *
     * @param id the ID of a user profile.
     * @return the requested profile.
     */
    Profile getById(String id);

    /**
     * Provides an existing user profile preview by its ID if any.
     *
     * @param id the ID of a user profile.
     * @return the requested profile.
     */
    Optional<Profile> findPreviewById(String id);

    /**
     * Provides a collections of existing user profile previews by their IDs.
     *
     * @param ids the IDs of profiles.
     * @return a collection of requested profiles.
     */
    Collection<Profile> findPreviews(Collection<String> ids);
}
