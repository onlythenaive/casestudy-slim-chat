package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

/**
 * Chat group preview provider.
 *
 * @author Ilia Gubarev
 */
public interface GroupPreviewProvider {

    /**
     * Provides a preview of an existing group by its ID if any.
     *
     * @param id the ID of an existing group.
     * @return the requested group.
     */
    Group getById(String id);
}
