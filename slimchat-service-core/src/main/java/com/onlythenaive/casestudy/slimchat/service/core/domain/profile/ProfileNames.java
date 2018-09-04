package com.onlythenaive.casestudy.slimchat.service.core.domain.profile;

import org.springframework.lang.Nullable;

/**
 * Utility helper for projecting profile names.
 *
 * @author Ilia Gubarev
 */
public final class ProfileNames {

    /**
     * Projects a readable name of specified profile.
     *
     * @param profile a profile to be projected.
     * @return the projected name.
     */
    @Nullable
    public static String pretty(@Nullable Profile profile) {
        if (profile == null) {
            return null;
        }
        String firstname = profile.getFirstname();
        if (firstname == null) {
            return profile.getId();
        }
        String lastname = profile.getLastname();
        return lastname != null ? firstname + " " + lastname : firstname;
    }

    private ProfileNames() {

    }
}
