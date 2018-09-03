package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupAccessor;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupEntity;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupRepository;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileAccessor;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileEntity;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileRepository;
import com.onlythenaive.casestudy.slimchat.service.core.utility.component.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.access.AccessLevel;

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

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message create(MessageInvoice invoice) {
        MessageInvoiceWrapper invoiceWrapper = MessageInvoiceWrapper.of(invoice, principalId());
        ensurePermission(invoiceWrapper);
        MessageEntity entity = messageFromInvoice(invoiceWrapper);
        MessageEntity insertedEntity = this.messagePersister.insert(entity);
        Message message = this.messageProjector.project(insertedEntity);
        handleAction(this.messageActionHandlers, handler -> handler.onMessageCreated(message));
        return message;
    }

    @Override
    public Message get(String id) {
        MessageEntity entity = this.messageRepository.getById(id);
        if (!entity.getObserverIds().contains(principalId())) {
            throw insufficientPrivileges();
        }
        return this.messageProjector.project(entity);
    }

    @Override
    public MessageSearchResult getSearchResult(MessageSearchInvoice invoice) {
        MessageEntity probe = MessageEntity.builder()
                .observerIds(new HashSet<>(Collections.singleton(principalId())))
                .chatId(invoice.getChatId())
                .build();
        Collection<MessageEntity> entities = this.messageRepository.findAll(Example.of(probe));
        Collection<Message> messages = entities.stream()
                .sorted(Comparator.comparing(MessageEntity::getCreatedAt).reversed())
                .map(this.messageProjector::project)
                .collect(Collectors.toList());
        return MessageSearchResult.builder()
                .items(messages)
                .build();
    }

    private void ensurePermission(MessageInvoiceWrapper invoiceWrapper) {
        String recipientId = invoiceWrapper.getRecipientId();
        if (recipientId != null) {
            ProfileEntity recipient = this.profileRepository.getById(recipientId);
            if (!recipient.getConnectedProfileIds().contains(principalId())) {
                throw insufficientPrivileges();
            }
        } else {
            GroupEntity groupEntity = this.groupRepository.getById(invoiceWrapper.getGroupId());
            this.groupAccessor.ensureAccess(groupEntity, AccessLevel.CONTRIBUTE);
        }
    }

    private MessageEntity messageFromInvoice(MessageInvoiceWrapper invoiceWrapper) {
        return MessageEntity.builder()
                .id(uuid())
                .authorId(principalId())
                .text(invoiceWrapper.getInvoice().getText())
                .recipientId(invoiceWrapper.getRecipientId())
                .groupId(invoiceWrapper.getGroupId())
                .chatId(invoiceWrapper.getInvoice().getChatId())
                .observerIds(observerIdsFromInvoice(invoiceWrapper))
                .createdAt(now())
                .build();
    }

    private Set<String> observerIdsFromInvoice(MessageInvoiceWrapper invoiceWrapper) {
        Set<String> observerIds = new HashSet<>();
        observerIds.add(principalId());
        if (invoiceWrapper.getRecipientId() != null) {
            observerIds.add(invoiceWrapper.getRecipientId());
        } else {
            GroupEntity groupEntity = this.groupRepository.getById(invoiceWrapper.getGroupId());
            observerIds.addAll(groupEntity.getParticipantIds());
        }
        return observerIds;
    }
}
