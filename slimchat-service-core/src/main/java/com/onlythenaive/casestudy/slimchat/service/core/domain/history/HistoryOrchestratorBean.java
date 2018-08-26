package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupActionAware;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupEntity;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupRepository;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageActionAware;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;

/**
 * Chat history orchestrator implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class HistoryOrchestratorBean extends GenericComponentBean implements MessageActionAware, GroupActionAware {

    @Autowired
    private HistoryPersister historyPersister;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public void onGroupCreated(Group group) {
        String groupId = group.getId();
        GroupEntity groupEntity = this.groupRepository.findById(groupId).orElseThrow(RuntimeException::new);
        groupEntity.getParticipantIds().forEach(participantId -> {
            HistoryEntity historyEntity = HistoryEntity.builder()
                    .ownerId(participantId)
                    .referencedGroupId(groupId)
                    .messageIds(new ArrayList<>())
                    .build();
            this.historyPersister.insert(historyEntity);
        });
    }

    @Override
    public void onMessageCreated(Message message) {
        Collection<HistoryEntity> historyEntities = ensureHistories(message);
        historyEntities.forEach(entity -> {
            entity.getMessageIds().add(message.getId());
            this.historyPersister.update(entity);
        });
    }

    private Collection<HistoryEntity> ensureHistories(Message message) {
        if (message.getRecipient() != null) {
            return ensurePersonalHistories(message);
        } else {
            return ensureGroupHistories(message);
        }
    }

    private Collection<HistoryEntity> ensurePersonalHistories(Message message) {
        Collection<HistoryEntity> historyEntities = new ArrayList<>();
        String recipientId = message.getRecipient().getId();

        Optional<HistoryEntity> ownHistoryEntityOptional = this.historyRepository.findByOwnerIdAndReferencedUserId(principalId(), recipientId);
        if (ownHistoryEntityOptional.isPresent()) {
            historyEntities.add(ownHistoryEntityOptional.get());
        } else {
            HistoryEntity ownHistoryEntity = HistoryEntity.builder()
                    .ownerId(principalId())
                    .messageIds(new ArrayList<>())
                    .referencedUserId(recipientId)
                    .build();
            this.historyPersister.insert(ownHistoryEntity);
            historyEntities.add(ownHistoryEntity);
        }

        Optional<HistoryEntity> otherHistoryEntityOptional = this.historyRepository.findByOwnerIdAndReferencedUserId(recipientId, principalId());
        if (otherHistoryEntityOptional.isPresent()) {
            historyEntities.add(otherHistoryEntityOptional.get());
        } else {
            HistoryEntity otherHistoryEntity = HistoryEntity.builder()
                    .ownerId(principalId())
                    .messageIds(new ArrayList<>())
                    .referencedUserId(recipientId)
                    .build();
            this.historyPersister.insert(otherHistoryEntity);
            historyEntities.add(otherHistoryEntity);
        }

        return historyEntities;
    }

    private Collection<HistoryEntity> ensureGroupHistories(Message message) {
        Collection<HistoryEntity> historyEntities = new ArrayList<>();
        String groupId = message.getGroup().getId();
        GroupEntity groupEntity = this.groupRepository.findById(groupId).orElseThrow(RuntimeException::new);
        groupEntity.getParticipantIds().forEach(participantId -> {
            Optional<HistoryEntity> participantHistoryEntityOptional = this.historyRepository.findByOwnerIdAndReferencedGroupId(participantId, groupId);
            if (participantHistoryEntityOptional.isPresent()) {
                historyEntities.add(participantHistoryEntityOptional.get());
            } else {
                HistoryEntity historyEntity = HistoryEntity.builder()
                        .ownerId(participantId)
                        .referencedGroupId(groupId)
                        .messageIds(new ArrayList<>())
                        .build();
                this.historyPersister.insert(historyEntity);
                historyEntities.add(historyEntity);
            }
        });
        return historyEntities;
    }
}
