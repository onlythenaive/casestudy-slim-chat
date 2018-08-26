package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.contact;

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
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/contacts")
public class ContactListViewPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private ContactFacade contactFacade;

    @GetMapping
    public ModelAndView get() {
        Collection<Profile> contacts = this.contactFacade.find();
        return render("contact-list-view", data(contacts));
    }

    private Object data(Collection<Profile> contacts) {
        Map<String, Object> data = new HashMap<>();
        data.put("contacts", contacts);
        return data;
    }
}
