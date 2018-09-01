package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.chat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageFacade;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/chats")
public class ChatListViewPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private MessageFacade messageFacade;

    @GetMapping
    public ModelAndView show() {
        Collection<Message> messages = this.messageFacade.getLatestFromEachChat();
        return renderChatListView(messages);
    }

    private ModelAndView renderChatListView(Collection<Message> messages) {
        Map<String, Object> data = new HashMap<>();
        data.put("messages", messages);
        return render("chat-list-view", data);
    }
}
