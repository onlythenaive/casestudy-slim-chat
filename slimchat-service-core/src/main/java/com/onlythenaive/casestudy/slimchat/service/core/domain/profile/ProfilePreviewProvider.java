package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import java.util.Collection;

import org.springframework.lang.Nullable;

/**
 * User profile preview provider.
 *
 * @author Ilia Gubarev
 */
public interface ProfilePreviewProvider {

    /**
     * Retrieves a preview of an existing profile if any.
     *
     * @param id the ID of an existing profile.
     * @return the resulting preview.
     */
    @Nullable
    Profile getById(String id);

    /**
     * Retrieves previews of existing profiles.
     *
     * @param ids the IDs of existing profiles.
     * @return a collection of previews.
     */
    Collection<Profile> getById(Collection<String> ids);
}
