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

import com.onlythenaive.casestudy.slimchat.service.core.domain.history.History;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.HistoryFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileFacade;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/chats")
public class ChatPersonalViewPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private HistoryFacade historyFacade;

    @Autowired
    private ProfileFacade profileFacade;

    @GetMapping("/{username}")
    public ModelAndView show(@PathVariable("username") String username) {
        Profile recipient = this.profileFacade.getById(username);
        Collection<History> histories = this.historyFacade.find();
        History existingHistory = null;
        for (History history : histories) {
            Profile referencedUser = history.getReferencedUser();
            if (referencedUser != null && referencedUser.getId().equals(principalId())) {
                existingHistory = this.historyFacade.get(history.getId());
                break;
            }
        }
        if (existingHistory == null) {
            existingHistory = History.builder()
                    .messages(new ArrayList<>())
                    .referencedUser(recipient)
                    .build();
        }
        return render("chat-personal-view", data(existingHistory));
    }

    private Object data(History history) {
        Map<String, Object> data = new HashMap<>();
        data.put("chat", history);
        return data;
    }
}
