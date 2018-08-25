package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import java.util.Collection;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;

/**
 * Contact operations facade.
 *
 * @author Ilia Gubarev
 */
public interface ContactFacade {

    /**
     * Retrieves all contacts of the current principal.
     *
     * @return the resulting contact collection.
     */
    Collection<Profile> find();

    /**
     * Removes a user from the contact list by its profile ID.
     *
     * @param profileId the ID of a user profile.
     */
    void remove(String profileId);
}
