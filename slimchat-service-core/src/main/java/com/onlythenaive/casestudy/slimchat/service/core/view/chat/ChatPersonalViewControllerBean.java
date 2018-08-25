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

import com.onlythenaive.casestudy.slimchat.service.core.domain.history.History;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.HistoryFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileFacade;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/chat")
public class ChatPersonalViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private HistoryFacade historyFacade;

    @Autowired
    private ProfileFacade profileFacade;

    @GetMapping("/{accountName}")
    public ModelAndView get(@PathVariable("accountName") String accountName) {
        Profile recipient = this.profileFacade.getByAccountName(accountName);
        Collection<History> histories = this.historyFacade.find();
        History existingHistory = null;
        for (History history : histories) {
            Profile referencedUser = history.getReferencedUser();
            if (referencedUser != null && referencedUser.getAccountName().equals(accountName)) {
                existingHistory = this.historyFacade.get(history.getId());
                break;
            }
        }
        Chat chat = Chat.builder()
                .history(existingHistory)
                .recipient(recipient)
                .build();
        return defaultView(data(chat));
    }

    @Override
    protected String defaultViewName() {
        return "chat-personal";
    }

    private Object data(Chat chat) {
        Map<String, Object> data = new HashMap<>();
        data.put("chat", chat);
        return data;
    }
}
