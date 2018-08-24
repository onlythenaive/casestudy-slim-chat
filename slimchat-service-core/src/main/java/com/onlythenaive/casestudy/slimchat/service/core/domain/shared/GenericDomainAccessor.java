package com.onlythenaive.casestudy.slimchat.service.core.domain.shared;

import java.util.Optional;

/**
 * Generic domain entity accessor.
 *
 * @param <E> the type of a domain entity.
 *
 * @author Ilia Gubarev
 */
public interface GenericDomainAccessor<E> {

    /**
     * Ensures if principal has sufficient privileges for provided entity.
     *
     * @param level required access level.
     * @param subject the entity to be checked.
     * @return the provided entity.
     */
    E ensureAccess(AccessLevel level, E subject);

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
