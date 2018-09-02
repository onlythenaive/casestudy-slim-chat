package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Collection;

/**
 * Profile operations facade.
 *
 * @author Ilia Gubarev
 */
public interface ProfileFacade {

    /**
     * Retrieves an existing profile by its ID (matches with its referenced account ID).
     *
     * @param id the ID of a profile.
     * @return the requested profile.
     */
    Profile getById(String id);

    /**
     * Retrieves search results among existing profiles by specified invoice.
     *
     * @param searchInvoice a search invoice.
     * @return the results of searching.
     */
    ProfileSearchResult getSearchResult(ProfileSearchInvoice searchInvoice);

    /**
     * Updates properties of an existing profile by its ID.
     *
     * @param id the ID of a profile.
     * @param invoice update properties to be applied.
     * @return the updated profile.
     */
    Profile update(String id, ProfileUpdateInvoice invoice);
}
