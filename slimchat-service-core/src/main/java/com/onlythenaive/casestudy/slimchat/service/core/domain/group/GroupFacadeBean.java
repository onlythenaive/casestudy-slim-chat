package com.onlythenaive.casestudy.slimchat.service.core.domain.group;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;

@Service
public class GroupFacadeBean extends GenericDomainComponentBean implements GroupFacade {

    @Autowired
    private GroupProjector groupProjector;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group create(GroupInvoice invoice) {
        Account account = authenticated();
        GroupEntity groupEntity = GroupEntity.builder()
                .id(uuid())
                .caption(invoice.getCaption())
                .participantIds(invoice.getParticipantIds())
                .moderatorIds(Collections.singleton(account.getId()))
                .createdAt(now())
                .build();
        return project(groupEntity);
    }

    @Override
    public Collection<Group> findByCaptionTemplate(String captionTemplate) {
        Account account = authenticated();
        GroupEntity probe = GroupEntity.builder()
                .participantIds(Collections.singleton(account.getId()))
                .caption(captionTemplate)
                .build();
        return this.groupRepository.findAll(Example.of(probe))
                .stream()
                .map(this::project)
                .collect(Collectors.toList());
    }

    @Override
    public Group getById(String id) {
        Account account = authenticated();
        GroupEntity groupEntity = getGroup(id);
        ensureParticipation(account, groupEntity);
        return project(groupEntity);
    }

    @Override
    public void inviteParticipant(String id, String participantId) {
        Account account = authenticated();
        GroupEntity groupEntity = getGroup(id);
        ensureModeration(account, groupEntity);
        // TODO: ensure participant is not there
        groupEntity.getParticipantIds().add(participantId);
        this.groupRepository.save(groupEntity);
    }

    @Override
    public void promoteParticipant(String id, String participantId) {
        Account account = authenticated();
        GroupEntity groupEntity = getGroup(id);
        ensureModeration(account, groupEntity);
        // TODO: ensure participant is there
        groupEntity.getModeratorIds().add(participantId);
        this.groupRepository.save(groupEntity);
    }

    @Override
    public void kickParticipant(String id, String participantId) {
        Account account = authenticated();
        GroupEntity groupEntity = getGroup(id);
        ensureModeration(account, groupEntity);
        // TODO: ensure participant is there
        groupEntity.getParticipantIds().remove(participantId);
        groupEntity.getModeratorIds().remove(participantId);
        this.groupRepository.save(groupEntity);
    }

    @Override
    public void leave(String id) {
        Account account = authenticated();
        GroupEntity groupEntity = getGroup(id);
        ensureParticipation(account, groupEntity);
        groupEntity.getParticipantIds().removeIf(account.getId()::equals);
        groupEntity.getModeratorIds().removeIf(account.getId()::equals);
        this.groupRepository.save(groupEntity);
    }

    @Override
    public Group updateCaption(String id, String caption) {
        Account account = authenticated();
        GroupEntity groupEntity = getGroup(id);
        ensureModeration(account, groupEntity);
        groupEntity.setCaption(caption);
        this.groupRepository.save(groupEntity);
        return project(groupEntity);
    }

    private Group project(GroupEntity entity) {
        return this.groupProjector.intoGroup(entity);
    }

    // TODO: move to provider
    private GroupEntity getGroup(String id) {
        // TODO: throw a meaningful exception
        return this.groupRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    private void ensureParticipation(Account account, GroupEntity groupEntity) {
        ensureAffiliation(account, groupEntity.getParticipantIds());
    }

    private void ensureModeration(Account account, GroupEntity groupEntity) {
        ensureAffiliation(account, groupEntity.getModeratorIds());
    }

    private void ensureAffiliation(Account account, Collection<String> participantIds) {
        if (participantIds.stream().noneMatch(account.getId()::equals)) {
            // TODO: throw a meaningful exception
            throw new RuntimeException();
        }
    }
}
