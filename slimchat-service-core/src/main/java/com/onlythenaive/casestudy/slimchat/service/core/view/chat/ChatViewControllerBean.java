package com.onlythenaive.casestudy.slimchat.service.core.view.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.chat.Chat;
import com.onlythenaive.casestudy.slimchat.service.core.domain.chat.ChatFacade;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/chat")
public class ChatViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private ChatFacade chatFacade;

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable("id") String id) {
        Chat chat = this.chatFacade.getChatById(id);
        return defaultView(chat);
    }

    @Override
    protected String defaultViewName() {
        return "chat";
    }
}
