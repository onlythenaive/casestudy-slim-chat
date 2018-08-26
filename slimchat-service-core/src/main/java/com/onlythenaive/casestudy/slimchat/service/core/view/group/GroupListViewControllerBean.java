package com.onlythenaive.casestudy.slimchat.service.core.view.group;

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
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/groups")
public class GroupListViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private GroupFacade groupFacade;

    @GetMapping
    public ModelAndView get() {
        Collection<Group> groups = this.groupFacade.findByParticipation();
        return defaultView(data(groups));
    }

    @Override
    protected String defaultViewName() {
        return "group-list";
    }

    private Object data(Collection<Group> groups) {
        Map<String, Object> data = new HashMap<>();
        data.put("groups", groups);
        return data;
    }
}
