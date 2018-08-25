package com.onlythenaive.casestudy.slimchat.service.core.utility.persistence;

import java.time.Instant;

/**
 * Generic persisted entity.
 *
 * @author Ilia Gubarev
 */
public interface PersistedEntity {

    /**
     * Gets teh ID of this entity.
     *
     * @return the ID of this entity.
     */
    String getId();

    /**
     * Sets a new ID of this entity.
     *
     * @param id a new ID of this entity.
     */
    void setId(String id);

    /**
     * Gets a timestamp of this entity creation.
     *
     * @return a timestamp of this entity creation.
     */
    Instant getCreatedAt();

    /**
     * Sets a new timestamp of this entity creation.
     *
     * @param createdAt a timestamp of this entity creation.
     */
    void setCreatedAt(Instant createdAt);

    /**
     * Gets a timestamp of the latest modification.
     *
     * @return a timestamp of the latest modification.
     */
    Instant getLastModifiedAt();

    /**
     * Sets a new timestamp of the latest modification.
     *
     * @param lastModifiedAt a timestamp of the latest modification.
     */
    void setLastModifiedAt(Instant lastModifiedAt);
}
