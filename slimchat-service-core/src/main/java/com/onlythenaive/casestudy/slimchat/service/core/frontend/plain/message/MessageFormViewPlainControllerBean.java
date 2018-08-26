package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageInvoice;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/messages")
public class MessageFormViewPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private MessageFacade messageFacade;

    @PostMapping("/create")
    public ModelAndView create(MessageInvoice invoice) {
        Message message = this.messageFacade.create(invoice);
        if (message.isAffiliatedToGroup()) {
            return redirect("chat/group/" + message.getGroup().getId());
        } else {
            return redirect("chats/" + message.getRecipient().getAccountName());
        }
    }
}
