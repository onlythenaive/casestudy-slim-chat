package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

/**
 * Profile operations facade.
 *
 * @author Ilia Gubarev
 */
public interface ProfileFacade {

    /**
     * Retrieves an existing profile by the name of its referenced account.
     *
     * @param accountName the name of a referenced account.
     * @return the requested profile.
     */
    Profile getByAccountName(String accountName);

    /**
     * Retrieves an existing profile by its ID (matches with its referenced account ID).
     *
     * @param id the ID of a profile.
     * @return the requested profile.
     */
    Profile getById(String id);

    /**
     * Updates properties of an existing profile by its ID.
     *
     * @param id the ID of a profile.
     * @param invoice update properties to be applied.
     * @return the updated profile.
     */
    Profile update(String id, ProfileUpdateInvoice invoice);
}
