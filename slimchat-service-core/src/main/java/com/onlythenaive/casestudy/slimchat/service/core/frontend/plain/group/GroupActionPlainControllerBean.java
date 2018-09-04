package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.thread.ThreadIdWrapper;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupInvoice;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/groups")
public class GroupActionPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private GroupFacade groupFacade;

    @PostMapping("/create")
    public ModelAndView create(GroupInvoice invoice) {
        Group group = this.groupFacade.create(invoice);
        String descriptor = descriptor(group.getId());
        return redirect("chats/" + descriptor);
    }

    private String descriptor(String groupId) {
        return ThreadIdWrapper.empty()
                .groupId(groupId)
                .toThreadId();
    }
}
