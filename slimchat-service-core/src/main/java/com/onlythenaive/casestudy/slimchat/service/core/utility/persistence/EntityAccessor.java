package com.onlythenaive.casestudy.slimchat.service.core.utility.persistence;

import java.util.Optional;

/**
 * Persisted entity accessor.
 *
 * @param <E> the type of a persisted entity.
 *
 * @author Ilia Gubarev
 */
public interface EntityAccessor<E extends PersistedEntity> {

    AccessLevel allowedAccessLevel(E subject);

    /**
     * Ensures if current operation has sufficient privileges for provided entity.
     *
     * @param level required access level.
     * @param subject the entity to be checked.
     * @return the provided entity.
     */
    E ensureAccess(AccessLevel level, E subject);

    /**
     * Find an existing entity by its ID.
     *
     * @param id the ID of an existing entity.
     * @return the requested entity.
     */
    E accessById(String id);

    /**
     * Find an existing entity by its ID and ensures access privileges.
     *
     * @param level required access level.
     * @param id the ID of an existing entity.
     * @return the requested entity.
     */
    E accessById(AccessLevel level, String id);

    /**
     * Find an existing entity by its ID if any and ensures access privileges.
     *
     * @param level required access level.
     * @param id the ID of an existing entity.
     * @return the requested entity.
     */
    Optional<E> accessByIdIfAny(AccessLevel level, String id);
}
