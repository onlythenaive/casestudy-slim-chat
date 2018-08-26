package com.onlythenaive.casestudy.slimchat.service.core.view.chat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.History;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.HistoryFacade;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/chats")
public class ChatListViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private HistoryFacade historyFacade;

    @Autowired
    private GroupFacade groupFacade;

    @GetMapping
    public ModelAndView get() {
        Collection<History> histories = this.historyFacade.find();
        Collection<Chat> chats = histories.stream()
                .map(history ->
                        Chat.builder()
                                .history(history)
                                .recipient(history.getReferencedUser())
                                .group(history.getReferencedGroup())
                                .build())
                .collect(Collectors.toList());
        return defaultView(data(chats));
    }

    @Override
    protected String defaultViewName() {
        return "chat-list";
    }

    private Object data(Collection<Chat> chats) {
        Map<String, Object> data = new HashMap<>();
        data.put("chats", chats);
        return data;
    }
}
