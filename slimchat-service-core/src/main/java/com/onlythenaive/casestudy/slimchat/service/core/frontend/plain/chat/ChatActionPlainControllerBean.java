package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.chat.ChatIdWrapper;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.HistoryFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.HistoryIdWrapper;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/chats")
public class ChatActionPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private HistoryFacade historyFacade;

    @PostMapping("/clear")
    public ModelAndView clear(ChatFormInput form) {
        HistoryIdWrapper historyIdWrapper = HistoryIdWrapper.fromChatId(form.getId()).ownerId(principalId());
        String historyId = historyIdWrapper.toHistoryId();
        this.historyFacade.clear(historyId);
        String descriptor = descriptor(historyIdWrapper);
        return redirect("chats/" + descriptor);
    }

    private String descriptor(HistoryIdWrapper historyIdWrapper) {
        ChatIdWrapper chatIdWrapper = historyIdWrapper.getChatIdWrapper();
        if (chatIdWrapper.getGroupId() != null) {
            return chatIdWrapper.getGroupId();
        }
        return chatIdWrapper.getCompanionId(principalId());
    }
}
