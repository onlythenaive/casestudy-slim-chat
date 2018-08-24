package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainComponentBean;

/**
 * Chat group provider implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class GroupProviderBean extends DomainComponentBean implements GroupProvider {

    @Autowired
    private GroupAccessor groupAccessor;

    @Autowired
    private GroupProjector groupProjector;

    @Override
    public Group getById(String id) {
        GroupEntity entity = this.groupAccessor.accessById(AccessLevel.VIEW, id);
        return this.groupProjector.project(entity);
    }

    @Override
    public Optional<Group> findPreviewById(String id) {
        return this.groupAccessor.accessByIdIfAny(AccessLevel.VIEW, id)
                .map(this.groupProjector::projectPreview);
    }
}
