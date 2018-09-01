package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.chat;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageSearchInvoice;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileFacade;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/chats")
public class ChatViewPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private GroupFacade groupFacade;

    @Autowired
    private MessageFacade messageFacade;

    @Autowired
    private ProfileFacade profileFacade;

    @GetMapping("/{profileId}")
    public ModelAndView showChat(@PathVariable("profileId") String profileId) {
        Profile profile = this.profileFacade.getById(profileId);
        MessageSearchInvoice invoice = MessageSearchInvoice.builder()
                .profileId1(profileId)
                .profileId2(principalId())
                .build();
        Collection<Message> messages = this.messageFacade.getSearchResult(invoice);
        return renderChatView(null, profile, messages);
    }

    @GetMapping("/group/{groupId}")
    public ModelAndView showGroupChat(@PathVariable("groupId") String groupId) {
        Group group = this.groupFacade.getById(groupId);
        MessageSearchInvoice invoice = MessageSearchInvoice.builder()
                .groupId(groupId)
                .build();
        Collection<Message> messages = this.messageFacade.getSearchResult(invoice);
        return renderChatView(group, null, messages);
    }

    private ModelAndView renderChatView(Group group, Profile profile, Collection<Message> messages) {
        Map<String, Object> data = new HashMap<>();
        data.put("group", group);
        data.put("profile", profile);
        data.put("messages", messages);
        return render("chat-view", data);
    }
}
