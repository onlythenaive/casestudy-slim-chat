package com.onlythenaive.casestudy.slimchat.service.core.view.contact;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.contact.ContactFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/contacts")
public class ContactListViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private ContactFacade contactFacade;

    @GetMapping
    public ModelAndView get() {
        Collection<Profile> contacts = this.contactFacade.find();
        return defaultView(data(contacts));
    }

    @Override
    protected String defaultViewName() {
        return "contact-list";
    }

    private Object data(Collection<Profile> contacts) {
        Map<String, Object> data = new HashMap<>();
        data.put("contacts", contacts);
        return data;
    }
}
