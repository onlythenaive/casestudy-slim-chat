package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;
import java.util.Optional;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.EntityRepository;

/**
 * Chat history repository.
 *
 * @author Ilia Gubarev
 */
public interface HistoryRepository extends EntityRepository<HistoryEntity> {

    /**
     * Finds chat histories by their owner ID.
     *
     * @param ownerId the ID of an existing user.
     * @return the resulting collection of histories.
     */
    Collection<HistoryEntity> findByOwnerId(String ownerId);

    /**
     * Finds an existing chat history by their owner's and referenced group's ID.
     *
     * @param ownerId the ID of the owner.
     * @param referencedGroupId the ID of a referenced group.
     * @return an existing chat history.
     */
    Optional<HistoryEntity> findByOwnerIdAndReferencedGroupId(String ownerId, String referencedGroupId);

    /**
     * Finds an existing chat history by their owner's and referenced user's ID.
     *
     * @param ownerId the ID of the owner.
     * @param referencedUserId the ID of a referenced user.
     * @return an existing chat history.
     */
    Optional<HistoryEntity> findByOwnerIdAndReferencedUserId(String ownerId, String referencedUserId);
}
