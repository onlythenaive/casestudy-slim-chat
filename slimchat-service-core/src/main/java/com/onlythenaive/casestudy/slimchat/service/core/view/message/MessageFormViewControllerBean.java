package com.onlythenaive.casestudy.slimchat.service.core.view.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.message.Message;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.message.MessageInvoice;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/message")
public class MessageFormViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private MessageFacade messageFacade;

    @PostMapping
    public ModelAndView post(MessageInvoice invoice) {
        Message message = this.messageFacade.create(invoice);
        if (message.isAffiliatedToGroup()) {
            return redirectToView("chat/group/" + message.getGroup().getId());
        } else {
            return redirectToView("chat/" + message.getRecipient().getAccountName());
        }
    }

    @Override
    protected String defaultViewName() {
        return "message";
    }
}
