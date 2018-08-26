package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupActionAware;
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

    @Override
    public void onGroupCreated(Group group) {
        if (this.historyRepository.existsByOwnerIdAndReferencedGroupId(principalId(), group.getId())) {
            return;
        }
        HistoryEntity entity = HistoryEntity.builder()
                .ownerId(principalId())
                .referencedGroupId(group.getId())
                .build();
        this.historyPersister.insert(entity);
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
            throw notSupported();
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
}
