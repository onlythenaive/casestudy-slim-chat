package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.thread.ThreadIdWrapper;
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
        String threadId = form.getThreadId();
        String historyId = HistoryIdWrapper.ofThreadIdAndOwnerId(threadId, principalId()).getHistoryId();
        this.historyFacade.clear(historyId);
        String descriptor = descriptor(form.getThreadId());
        return redirect("chats/" + descriptor);
    }

    private String descriptor(String threadId) {
        ThreadIdWrapper threadIdWrapper = ThreadIdWrapper.ofThreadId(threadId);
        if (threadIdWrapper.containsGroupId()) {
            return threadIdWrapper.getGroupId();
        }
        return threadIdWrapper.getCompanionId(principalId());
    }
}
