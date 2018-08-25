package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainComponentBean;

/**
 * Chat group operations facade implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class GroupFacadeBean extends DomainComponentBean implements GroupFacade {

    @Autowired(required = false)
    private Collection<GroupActionAware> groupActionHandlers;

    @Autowired
    private GroupProjector groupProjector;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupAccessor groupAccessor;

    @Autowired
    private GroupPersister groupPersister;

    @Autowired
    private GroupProvider groupProvider;

    @Override
    public Group create(GroupInvoice invoice) {
        GroupEntity entity = createGroupFromInvoice(invoice);
        this.groupPersister.insert(entity);
        Group group = project(entity);
        handleAction(this.groupActionHandlers, handler -> handler.onGroupCreated(group));
        return group;
    }

    @Override
    public Collection<Group> findByCaption(String captionTemplate) {
        return this.groupRepository.findAll(groupExampleFromCaptionTemplate(captionTemplate))
                .stream()
                .map(this.groupProjector::projectPreview)
                .collect(Collectors.toList());
    }

    @Override
    public Group getById(String id) {
        return this.groupProvider.getById(id);
    }

    @Override
    public void inviteUser(String id, String userId) {
        GroupEntity entity = this.groupAccessor.accessById(AccessLevel.MANAGE, id);
        // TODO: check if user exists
        if (!entity.getParticipantIds().contains(userId)) {
            entity.getParticipantIds().add(id);
        }
        this.groupPersister.update(entity);
    }

    @Override
    public void promoteParticipant(String id, String participantId) {
        GroupEntity entity = this.groupAccessor.accessById(AccessLevel.MANAGE, id);
        if (entity.getParticipantIds().contains(participantId)) {
            entity.getModeratorIds().add(participantId);
        }
        this.groupPersister.update(entity);
    }

    @Override
    public void kickParticipant(String id, String participantId) {
        GroupEntity entity = this.groupAccessor.accessById(AccessLevel.MANAGE, id);
        entity.getParticipantIds().remove(participantId);
        entity.getModeratorIds().remove(participantId);
        // TODO: handle "last moderator" scenario
        // TODO: handle "last participant" scenario
        this.groupPersister.update(entity);
    }

    @Override
    public void leave(String id) {
        GroupEntity entity = this.groupAccessor.accessById(AccessLevel.VIEW, id);
        entity.getParticipantIds().remove(principalId());
        entity.getModeratorIds().remove(principalId());
        // TODO: handle "last moderator" scenario
        // TODO: handle "last participant" scenario
        this.groupPersister.update(entity);
    }

    @Override
    public Group updateCaption(String id, String caption) {
        GroupEntity entity = this.groupAccessor.accessById(AccessLevel.EDIT, id);
        entity.setCaption(caption);
        this.groupPersister.update(entity);
        return project(entity);
    }

    private Example<GroupEntity> groupExampleFromCaptionTemplate(String captionTemplate) {
        GroupEntity probe = GroupEntity.builder()
                .caption(captionTemplate)
                .participantIds(Collections.singleton(principalId()))
                .build();
        return Example.of(probe);
    }

    private GroupEntity createGroupFromInvoice(GroupInvoice invoice) {
        return GroupEntity.builder()
                .caption(invoice.getCaption())
                .participantIds(invoice.getParticipantIds())
                .moderatorIds(invoice.getParticipantIds())
                .build();
    }

    private Group project(GroupEntity entity) {
        return this.groupProjector.project(entity);
    }
}
