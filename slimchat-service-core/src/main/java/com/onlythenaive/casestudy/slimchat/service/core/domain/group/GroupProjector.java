package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

/**
 * Chat group projector.
 *
 * @author Ilia Gubarev
 */
public interface GroupProjector {

    /**
     * Create a projection of an existing chat group from its entity.
     *
     * @param entity an existing entity to be projected.
     * @return the resulting projection.
     */
    Group project(GroupEntity entity);

    /**
     * Create a brief projection of an existing chat group from its entity.
     *
     * @param entity an existing entity to be projected.
     * @return the resulting projection.
     */
    Group projectPreview(GroupEntity entity);
}
