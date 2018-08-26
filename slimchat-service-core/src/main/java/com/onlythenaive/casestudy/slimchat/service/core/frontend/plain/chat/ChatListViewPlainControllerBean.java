package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.chat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.History;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.HistoryFacade;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/chats")
public class ChatListViewPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private HistoryFacade historyFacade;

    @Autowired
    private GroupFacade groupFacade;

    @GetMapping
    public ModelAndView show() {
        Collection<History> histories = this.historyFacade.find();
        return render("chat-list-view", data(histories));
    }

    private Object data(Collection<History> histories) {
        Map<String, Object> data = new HashMap<>();
        data.put("chats", histories);
        return data;
    }
}
