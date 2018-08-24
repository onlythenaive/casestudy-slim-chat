package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupActionAware;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageActionAware;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainComponentBean;

/**
 * Chat history orchestrator implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class HistoryOrchestratorBean extends DomainComponentBean implements MessageActionAware, GroupActionAware {

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
        if (message.getRecipient() == null) {
            return;
        }
        String recipientId = message.getRecipient().getId();
        if (this.historyRepository.existsByOwnerIdAndReferencedUserId(principalId(), recipientId)) {
            return;
        }
        HistoryEntity entity = HistoryEntity.builder()
                .ownerId(principalId())
                .referencedUserId(recipientId)
                .build();
        this.historyPersister.insert(entity);
    }
}
