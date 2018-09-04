package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.chat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.thread.ThreadIdWrapper;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.History;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.HistoryFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.HistoryIdWrapper;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileFacade;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/chats")
public class ChatViewPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private HistoryFacade historyFacade;

    @Autowired
    private ProfileFacade profileFacade;

    @Autowired
    private GroupFacade groupFacade;

    @GetMapping("/{descriptor}")
    public ModelAndView show(@PathVariable("descriptor") String descriptor) {
        String threadId = threadId(descriptor);
        String historyId = HistoryIdWrapper.fromThreadId(threadId).ownerId(principalId()).toHistoryId();
        History history = this.historyFacade.getById(historyId);
        return renderHistoryView(history);
    }

    private String threadId(String descriptor) {
        ThreadIdWrapper threadIdWrapper = new ThreadIdWrapper();
        try {
            Group group = groupFacade.getById(descriptor);
            threadIdWrapper.groupId(descriptor);
        } catch (Exception e) {
            threadIdWrapper.profileId1(descriptor);
            threadIdWrapper.profileId2(principalId());
        }
        return threadIdWrapper.toThreadId();
    }

    private ModelAndView renderHistoryView(History history) {
        Map<String, Object> data = new HashMap<>();
        data.put("chat", history);
        return render("chat-view", data);
    }
}
