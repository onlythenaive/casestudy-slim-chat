package com.onlythenaive.casestudy.slimchat.service.core.domain.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.chat.ChatEntity;
import com.onlythenaive.casestudy.slimchat.service.core.domain.chat.ChatRepository;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;

@Service
public class MessageFacadeBean extends GenericDomainComponentBean implements MessageFacade {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageProjector messageProjector;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message createMessage(MessageInvoice invoice) {

        // NOTE: retrieve current authenticated account
        Account account = authenticated();

        // NOTE: retrieve a chat by its ID if any
        String chatId = invoice.getChatId();
        ChatEntity chatEntity = this.chatRepository.findById(chatId).orElseThrow(this::chatNotFound);

        // NOTE: if account does not participate in the chat
        if (!chatEntity.getParticipantIds().contains(account.getId())) {
            throw chatNotFound();
        }

        // NOTE: create a new message from the invoice
        MessageEntity entity = MessageEntity.builder()
                .id(uuid())
                .authorId(account.getId())
                .chatId(chatId)
                .createdAt(now())
                .text(invoice.getText())
                .build();

        // NOTE: persist the created message
        this.messageRepository.insert(entity);

        // NOTE: return a projection of the created message
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
