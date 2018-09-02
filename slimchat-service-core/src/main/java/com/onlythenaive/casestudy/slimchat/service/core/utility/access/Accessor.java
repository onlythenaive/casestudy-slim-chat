package com.onlythenaive.casestudy.slimchat.service.core.utility.access;

/**
 * Accessor level checker.
 *
 * @param <T> the type of a subject.
 *
 * @author Ilia Gubarev
 */
public interface Accessor<T> {

    /**
     * Derives the highest allowed access level for specified subject.
     *
     * @param subject a subject to be accessed.
     * @return the resulting access level.
     */
    AccessLevel allowedAccessLevel(T subject);

    /**
     * Ensures if current operation has sufficient privileges for specified subject.
     *
     * @param subject a subject to be checked.
     * @param level required access level.
     */
    void ensureAccess(T subject, AccessLevel level);
}
