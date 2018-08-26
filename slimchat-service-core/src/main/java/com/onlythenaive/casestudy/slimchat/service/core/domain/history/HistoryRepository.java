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
     * Checks if a chat history for specified group ID exists.
     *
     * @param ownerId the ID of the owner.
     * @param referencedGroupId the ID of a referenced group.
     * @return true if such history exists.
     */
    boolean existsByOwnerIdAndReferencedGroupId(String ownerId, String referencedGroupId);

    Optional<HistoryEntity> findByOwnerIdAndReferencedUserId(String ownerId, String referencedUserId);

    /**
     * Checks if a chat history for specified user ID exists.
     *
     * @param ownerId the ID of the owner.
     * @param referencedUserId the ID of a referenced user.
     * @return true if such history exists.
     */
    boolean existsByOwnerIdAndReferencedUserId(String ownerId, String referencedUserId);
}
