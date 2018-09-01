package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ReplaceRootOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupAccessor;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupEntity;
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

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message create(MessageInvoice invoice) {
        ensurePermission(invoice);
        MessageEntity entity = messageFromInvoice(invoice);
        this.messagePersister.insert(entity);
        Message message = this.messageProjector.project(entity);
        handleAction(this.messageActionHandlers, handler -> handler.onMessageCreated(message));
        return message;
    }

    @Override
    public Message get(String id) {
        MessageEntity entity = this.messageRepository.findById(id).orElseThrow(() -> notFoundById("message", id));
        if (!entity.getObserverIds().contains(principalId())) {
            throw insufficientPrivileges();
        }
        return this.messageProjector.project(entity);
    }

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Collection<Message> getLatestFromEachChat() {

        MatchOperation matchStage = Aggregation.match(new Criteria("observerIds").in(principalId()));

        SortOperation sortStage = Aggregation.sort(Sort.Direction.DESC, "createdAt");

        GroupOperation groupStage = Aggregation.group("chatDescriptor").first(Aggregation.ROOT).as("firstMessageInChat");


        ReplaceRootOperation replaceRootStage = Aggregation.replaceRoot("firstMessageInChat");

        TypedAggregation<MessageEntity> aggregation = Aggregation.newAggregation(MessageEntity.class, matchStage, sortStage, groupStage, replaceRootStage);


        Collection<Message> messages = this.mongoTemplate.aggregate(aggregation, MessageEntity.class).getMappedResults().stream().map(this.messageProjector::project).collect(Collectors.toList());

        return messages;
    }

    @Override
    public Collection<Message> getSearchResult(MessageSearchInvoice invoice) {
        MessageEntity probe = MessageEntity.builder()
                .observerIds(new HashSet<>(Collections.singleton(principalId())))
                .chatDescriptor(MessageChatDescriptorBuilder.of(invoice).build())
                .build();
        Collection<MessageEntity> entities = this.messageRepository.findAll(Example.of(probe));
        return entities.stream()
                .sorted(Comparator.comparing(MessageEntity::getCreatedAt).reversed())
                .map(this.messageProjector::project)
                .collect(Collectors.toList());
    }

    private void ensurePermission(MessageInvoice invoice) {
        String recipientId = invoice.getRecipientId();
        if (recipientId != null) {
            ProfileEntity recipient = this.profileAccessor.accessById(AccessLevel.PREVIEW, recipientId);
            if (!recipient.getConnectedProfileIds().contains(principalId())) {
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
                .observerIds(observerIdsFromInvoice(invoice))
                .createdAt(now())
                .build();
    }

    private Set<String> observerIdsFromInvoice(MessageInvoice invoice) {
        Set<String> observerIds = new HashSet<>();
        observerIds.add(principalId());
        if (invoice.getRecipientId() != null) {
            observerIds.add(invoice.getRecipientId());
        } else {
            GroupEntity groupEntity = this.groupAccessor.accessById(invoice.getGroupId());
            observerIds.addAll(groupEntity.getParticipantIds());
        }
        return observerIds;
    }
}
