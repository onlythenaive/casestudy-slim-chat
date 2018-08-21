package com.onlythenaive.casestudy.slimchat.service.core.domain.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.GenericDomainComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;

@Service
public class ChatFacadeBean extends GenericDomainComponentBean implements ChatFacade {

    @Autowired
    private ChatProjector chatProjector;

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(ChatInvoice invoice) {

        // NOTE: retrieve current authenticated account
        Account account = authenticated();

        // NOTE: create a new chat from the invoice
        ChatEntity entity = ChatEntity.builder()
                .id(uuid())
                .caption(invoice.getCaption())
                .personal(invoice.getParticipantIds().size() < 3)
                .adminIds(invoice.getAdminIds())
                .participantIds(invoice.getParticipantIds())
                .build();

        // NOTE: persist the created chat
        this.chatRepository.insert(entity);

        // NOTE: return a projection of the created chat
        return this.chatProjector.intoChat(entity);
    }

    @Override
    public Chat getChatById(String id) {

        // NOTE: retrieve current authenticated account
        Account account = authenticated();

        // NOTE: retrieve the requested chat
        ChatEntity entity = this.chatRepository.getById(id);
        if (entity == null) {
            throw chatNotFound();
        }

        // NOTE: check for participation
        if (!entity.getParticipantIds().contains(account.getId())) {
            throw chatViewRestricted();
        }

        // NOTE: return a projection of the chat
        return this.chatProjector.intoChat(entity);
    }

    private OperationException chatNotFound() {
        throw OperationException.builder()
                .category(ExceptionCategory.LOGIC)
                .comment("Chat does not exist")
                .textcode("x.logic.chat.not-found")
                .build();
    }

    private OperationException chatViewRestricted() {
        throw OperationException.builder()
                .category(ExceptionCategory.LOGIC)
                .comment("Chat view restricted")
                .textcode("x.logic.chat.view-restricted")
                .build();
    }
}
