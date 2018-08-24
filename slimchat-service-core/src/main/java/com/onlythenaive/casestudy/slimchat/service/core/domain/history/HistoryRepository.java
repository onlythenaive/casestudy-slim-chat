package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.Collection;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainRepository;

/**
 * Chat history repository.
 *
 * @author Ilia Gubarev
 */
public interface HistoryRepository extends DomainRepository<HistoryEntity> {

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

    /**
     * Checks if a chat history for specified user ID exists.
     *
     * @param ownerId the ID of the owner.
     * @param referencedUserId the ID of a referenced user.
     * @return true if such history exists.
     */
    boolean existsByOwnerIdAndReferencedUserId(String ownerId, String referencedUserId);
}
