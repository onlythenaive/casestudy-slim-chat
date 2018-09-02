package com.onlythenaive.casestudy.slimchat.service.core.utility.access;

/**
 * Access level.
 *
 * @author Ilia Gubarev
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

    /**
     * Checks if this level has a greater weight than another one.
     *
     * @param another another level to be checked against.
     * @return true if the check is positive.
     */
    public boolean greaterThan(AccessLevel another) {
        return this.weight > another.weight;
    }

    /**
     * Checks if this level has a greater or equal weight with another one.
     *
     * @param another another level to be checked against.
     * @return true if the check is positive.
     */
    public boolean greaterOrEqual(AccessLevel another) {
        return this.weight >= another.weight;
    }
}
