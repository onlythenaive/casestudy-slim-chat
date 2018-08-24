package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Collection;

/**
 * Chat group operations facade.
 *
 * @author Ilia Gubarev
 */
public interface GroupFacade {

    /**
     * Creates a new chat group.
     *
     * @param invoice an invoice for a new chat group creation.
     * @return the created group.
     */
    Group create(GroupInvoice invoice);

    /**
     * Finds existing chat groups by their captions.
     *
     * @param captionTemplate a template of group's caption.
     * @return a list of found existing groups.
     */
    Collection<Group> findByCaption(String captionTemplate);

    /**
     * Retrieves n existing group by its ID.
     *
     * @param id the ID of an existing group.
     * @return the requested group.
     */
    Group getById(String id);

    /**
     * Invites an existing user into specified group.
     *
     * @param id the ID of an existing group.
     * @param userId the ID of an existing user.
     */
    void inviteUser(String id, String userId);

    /**
     * Promotes a participant to a moderator of specified group.
     *
     * @param id the ID of an existing group.
     * @param participantId the ID of a participant.
     */
    void promoteParticipant(String id, String participantId);

    /**
     * Removes a participant from specified group.
     *
     * @param id the ID of an existing group.
     * @param participantId the ID of a participant.
     */
    void kickParticipant(String id, String participantId);

    /**
     * Removes the principal from specified group.
     *
     * @param id the ID of an existing group.
     */
    void leave(String id);

    /**
     * Updates the caption of an existing group.
     *
     * @param id the ID of an existing group.
     * @param caption a new caption to be used.
     * @return the resulting group.
     */
    Group updateCaption(String id, String caption);
}
