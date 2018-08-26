package com.onlythenaive.casestudy.slimchat.service.core.view.chat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.History;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.HistoryFacade;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/chat/group")
public class ChatGroupViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private HistoryFacade historyFacade;

    @Autowired
    private GroupFacade groupFacade;

    @GetMapping("/{groupId}")
    public ModelAndView get(@PathVariable("groupId") String groupId) {
        Group group = this.groupFacade.getById(groupId);
        Collection<History> histories = this.historyFacade.find();
        History existingHistory = null;
        for (History history : histories) {
            Group referencedGroup = history.getReferencedGroup();
            if (referencedGroup != null && referencedGroup.getId().equals(groupId)) {
                existingHistory = this.historyFacade.get(history.getId());
                break;
            }
        }
        Chat chat = Chat.builder()
                .history(existingHistory)
                .group(group)
                .build();
        return defaultView(data(chat));
    }

    @Override
    protected String defaultViewName() {
        return "chat-group";
    }

    private Object data(Chat chat) {
        Map<String, Object> data = new HashMap<>();
        data.put("chat", chat);
        return data;
    }
}
