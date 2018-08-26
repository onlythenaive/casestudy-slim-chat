package com.onlythenaive.casestudy.slimchat.service.core.view.group;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupInvoice;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/groups/create")
public class GroupActionControllerBean extends GenericViewControllerBean {

    @Autowired
    private GroupFacade groupFacade;

    @PostMapping
    public ModelAndView post(GroupInvoice invoice) {
        Group group = this.groupFacade.create(invoice);
        return redirectToView("chat/group/" + group.getId());
    }

    @Override
    protected String defaultViewName() {
        return "group";
    }
}
