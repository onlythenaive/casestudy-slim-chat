package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupAccessor;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileAccessor;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileEntity;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.AccessLevel;

/**
 * Chat message operations facade implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class MessageFacadeBean extends GenericComponentBean implements MessageFacade {

    @Autowired
    private GroupAccessor groupAccessor;

    @Autowired(required = false)
    private Collection<MessageActionAware> messageActionHandlers;

    @Autowired
    private MessagePersister messagePersister;

    @Autowired
    private MessageProjector messageProjector;

    @Autowired
    private ProfileAccessor profileAccessor;

    @Override
    public Message create(MessageInvoice invoice) {
        ensurePermission(invoice);
        MessageEntity entity = messageFromInvoice(invoice);
        this.messagePersister.insert(entity);
        Message message = this.messageProjector.project(entity);
        handleAction(this.messageActionHandlers, handler -> handler.onMessageCreated(message));
        return message;
    }

    private void ensurePermission(MessageInvoice invoice) {
        ProfileEntity recipient = this.profileAccessor.accessById(AccessLevel.PREVIEW, invoice.getRecipientId());
        if (recipient != null) {
            if (!recipient.getConnectedUserIds().contains(principalId())) {
                throw insufficientPrivileges();
            }
        } else {
            this.groupAccessor.accessById(AccessLevel.CONTRIBUTE, invoice.getGroupId());
        }
    }

    private MessageEntity messageFromInvoice(MessageInvoice invoice) {
        return MessageEntity.builder()
                .id(uuid())
                .authorId(principalId())
                .text(invoice.getText())
                .recipientId(invoice.getRecipientId())
                .groupId(invoice.getGroupId())
                .createdAt(now())
                .build();
    }
}
