package com.onlythenaive.casestudy.slimchat.service.core.utility.persistence;

/**
 * Persisted entity access level.
 *
 * @author Ili Gubarev
 */
public enum AccessLevel {

    /**
     * Access control is bypassed.
     */
    BYPASS,

    /**
     * Brief information available only.
     */
    PREVIEW,

    /**
     * Detailed view of resource information.
     */
    VIEW,

    /**
     * Write-based privilege allowing additional information creation/contribution.
     */
    CONTRIBUTE,

    /**
     * Standard update privilege of resource.
     */
    EDIT,

    /**
     * High-privileged update access for crucial operations, f.e. deleting.
     */
    MANAGE
}
