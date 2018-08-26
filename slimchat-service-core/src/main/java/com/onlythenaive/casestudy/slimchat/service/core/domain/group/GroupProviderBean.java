package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.AccessLevel;

/**
 * Chat group provider implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class GroupProviderBean extends GenericComponentBean implements GroupProvider {

    @Autowired
    private GroupAccessor groupAccessor;

    @Autowired
    private GroupProjector groupProjector;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group getById(String id) {
        GroupEntity entity = this.groupAccessor.accessById(AccessLevel.VIEW, id);
        return this.groupProjector.project(entity);
    }

    @Override
    public Optional<Group> findPreviewById(String id) {
        return this.groupAccessor.accessByIdIfAny(AccessLevel.VIEW, id)
                .map(this::projectPreview);
    }

    @Override
    public Collection<Group> findPreviewsByParticipantId(String participantId) {
        return this.groupRepository.findByParticipantIds(participantId).stream()
                .map(this::projectPreview)
                .collect(Collectors.toList());
    }

    private Group projectPreview(GroupEntity entity) {
        return this.groupProjector.projectPreview(entity);
    }
}
