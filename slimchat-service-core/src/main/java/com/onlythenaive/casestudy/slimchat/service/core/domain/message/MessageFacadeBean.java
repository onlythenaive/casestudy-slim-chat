package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupEntity;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupRepository;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileEntity;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileRepository;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;

@Service
public class MessageFacadeBean extends GenericDomainComponentBean implements MessageFacade {

    private GroupRepository groupRepository;

    @Autowired
    private MessageProjector messageProjector;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Message createMessage(MessageInvoice invoice) {
        if (invoice.getPersonal()) {
            String recipientId = invoice.getRecipientId();
            ProfileEntity profileEntity = this.profileRepository.findById(recipientId).orElseThrow(RuntimeException::new);
            // TODO: check recipient in the contacts
        } else {
            String groupId = invoice.getGroupId();
            GroupEntity groupEntity = this.groupRepository.findById(groupId).orElseThrow(RuntimeException::new);
            // TODO: check participation in the group
        }
        MessageEntity entity = MessageEntity.builder()
                .id(uuid())
                .authorId(principalId())
                .text(invoice.getText())
                .personal(invoice.getPersonal())
                .recipientId(invoice.getRecipientId())
                .groupId(invoice.getGroupId())
                .createdAt(now())
                .build();
        this.messageRepository.insert(entity);
        return this.messageProjector.intoMessage(entity);
    }

    private OperationException chatNotFound() {
        throw OperationException.builder()
                .category(ExceptionCategory.LOGIC)
                .comment("Chat does not exist")
                .textcode("x.logic.chat.not-found")
                .build();
    }
}
