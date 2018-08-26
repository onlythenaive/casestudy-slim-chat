package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.group;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.group.Group;
import com.onlythenaive.casestudy.slimchat.service.core.domain.group.GroupFacade;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/groups")
public class GroupListViewPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private GroupFacade groupFacade;

    @GetMapping
    public ModelAndView show() {
        Collection<Group> groups = this.groupFacade.findByParticipation();
        return render("group-list-view", data(groups));
    }

    private Object data(Collection<Group> groups) {
        Map<String, Object> data = new HashMap<>();
        data.put("groups", groups);
        return data;
    }
}
