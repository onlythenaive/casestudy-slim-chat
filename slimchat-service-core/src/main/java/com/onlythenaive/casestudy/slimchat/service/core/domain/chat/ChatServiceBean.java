package com.onlythenaive.casestudy.slimchat.service.core.domain.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.exception.ExceptionCategory;
import com.onlythenaive.casestudy.slimchat.service.core.exception.OperationException;
import com.onlythenaive.casestudy.slimchat.service.core.security.account.Account;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.Authentication;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationContext;

@Service
public class ChatServiceBean implements ChatService {

    @Autowired
    private AuthenticationContext authenticationContext;

    @Override
    public Chat createChat(Chat chat) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Chat getChatById(String id) {
        mustBeAuthenticated();
        Chat chat = retrieveChatById(id);
        mustHaveAccessPrivilege(chat);
        return chat;
    }

    @Override
    public Chat updateChat(String id, Chat chat) {
        throw new UnsupportedOperationException();
    }

    private Account mustBeAuthenticated() {
        Authentication authentication = this.authenticationContext.getAuthentication();
        if (authentication == null) {
            throw OperationException.builder()
                    .category(ExceptionCategory.SECURITY)
                    .comment("Not authenticated")
                    .textcode("x.security.not-authenticated")
                    .build();
        }
        return authentication.getAccount();
    }

    private Chat retrieveChatById(String id) {
        throw new UnsupportedOperationException();
    }

    private void mustHaveAccessPrivilege(Chat chat) {
        boolean haveAccess = chat.getMembers().stream()
                .map(Profile::getId)
                .anyMatch(id -> id.equals(mustBeAuthenticated().getId()));
        if (!haveAccess) {
            throw OperationException.builder()
                    .category(ExceptionCategory.SECURITY)
                    .comment("Not authorized")
                    .textcode("x.security.not-authorized")
                    .build();
        }
    }
}
