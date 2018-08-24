package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

/**
 * Component aware of group-related actions.
 *
 * @author Ilia Gubarev
 */
public interface GroupActionAware {

    /**
     * Handles group creation.
     *
     * @param group a new created group.
     */
    void onGroupCreated(Group group);
}
