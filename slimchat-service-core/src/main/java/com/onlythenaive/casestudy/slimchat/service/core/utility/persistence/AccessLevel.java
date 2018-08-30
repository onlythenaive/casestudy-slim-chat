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
    BYPASS(0),

    /**
     * Brief information available only.
     */
    PREVIEW(100),

    /**
     * Detailed view of resource information.
     */
    VIEW(200),

    /**
     * Write-based privilege allowing additional information creation/contribution.
     */
    CONTRIBUTE(300),

    /**
     * Standard update privilege of resource.
     */
    EDIT(400),

    /**
     * High-privileged update access for crucial operations, f.e. deleting.
     */
    MANAGE(500);

    private final int weight;

    AccessLevel(int weight) {
        this.weight = weight;
    }

    public boolean greaterThan(AccessLevel other) {
        return this.weight > other.weight;
    }

    public boolean greaterOrEqual(AccessLevel other) {
        return this.weight >= other.weight;
    }
}
