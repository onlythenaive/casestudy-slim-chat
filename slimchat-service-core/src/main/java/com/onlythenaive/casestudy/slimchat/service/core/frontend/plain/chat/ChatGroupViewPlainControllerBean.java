package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.chat;

import java.util.ArrayList;
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
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/chats/group")
public class ChatGroupViewPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private HistoryFacade historyFacade;

    @Autowired
    private GroupFacade groupFacade;

    @GetMapping("/{groupId}")
    public ModelAndView show(@PathVariable("groupId") String groupId) {
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
        if (existingHistory == null) {
            existingHistory = History.builder()
                    .messages(new ArrayList<>())
                    .referencedGroup(group)
                    .build();
        }
        return render("chat-group-view", data(existingHistory));
    }

    private Object data(History history) {
        Map<String, Object> data = new HashMap<>();
        data.put("chat", history);
        return data;
    }
}
