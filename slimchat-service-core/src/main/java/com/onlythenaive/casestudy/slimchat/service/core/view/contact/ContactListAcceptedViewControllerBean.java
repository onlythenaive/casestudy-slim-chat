package com.onlythenaive.casestudy.slimchat.service.core.view.contact;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.contact.Contact;
import com.onlythenaive.casestudy.slimchat.service.core.domain.contact.ContactFacade;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/contacts")
public class ContactListAcceptedViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private ContactFacade contactFacade;

    @GetMapping
    public ModelAndView get() {
        authenticated();
        Collection<Contact> contacts = this.contactFacade.findAllAcceptedContacts();
        return defaultView(data("My contacts", contacts));
    }

    @PostMapping
    public ModelAndView post(ContactModificationForm form) {
        authenticated();
        this.contactFacade.cancelContactById(form.getContactId());
        Collection<Contact> contacts = this.contactFacade.findAllAcceptedContacts();
        return defaultView(data("My contacts", contacts));
    }

    @GetMapping("/pending")
    public ModelAndView getPending() {
        authenticated();
        Collection<Contact> contacts = this.contactFacade.findAllPendingContacts();
        return defaultView(data("My pending requested", contacts));
    }

    @PostMapping("/pending")
    public ModelAndView postPending(ContactModificationForm form) {
        authenticated();
        this.contactFacade.cancelContactById(form.getContactId());
        Collection<Contact> contacts = this.contactFacade.findAllPendingContacts();
        return defaultView(data("My pending requested", contacts));
    }

    @GetMapping("/requested")
    public ModelAndView getRequested() {
        authenticated();
        Collection<Contact> contacts = this.contactFacade.findAllRequestedContacts();
        return defaultView(data("Requested to me", contacts));
    }

    @PostMapping("/requested")
    public ModelAndView postRequested(ContactModificationForm form) {
        authenticated();
        this.contactFacade.acceptContactById(form.getContactId());
        Collection<Contact> contacts = this.contactFacade.findAllRequestedContacts();
        return defaultView(data("Requested to me", contacts));
    }

    @Override
    protected String defaultViewName() {
        return "contact-list";
    }

    private Object data(String groupName, Collection<Contact> contacts) {
        Map<String, Object> data = new HashMap<>();
        data.put("groupName", groupName);
        data.put("contacts", contacts);
        return data;
    }
}
