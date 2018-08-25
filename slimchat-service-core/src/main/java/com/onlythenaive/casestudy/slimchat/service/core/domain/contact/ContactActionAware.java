package com.onlythenaive.casestudy.slimchat.service.core.domain.contact;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;

/**
 * Component aware of contact-related actions.
 *
 * @author Ilia Gubarev
 */
public interface ContactActionAware {

    /**
     * Handles creation of a new contact.
     *
     * @param actor the profile of an acting user.
     * @param object the profile of another user.
     */
    default void onContactCreated(Profile actor, Profile object) {

    }

    /**
     * Handles deletion of an existing contact.
     *
     * @param actor the profile of an acting user.
     * @param object the profile of another user.
     */
    default void onContactDeleted(Profile actor, Profile object) {

    }
}
