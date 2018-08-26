package com.onlythenaive.casestudy.slimchat.service.core.view.profile;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileFacade;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/profiles")
public class ProfileListViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private ProfileFacade profileFacade;

    @GetMapping
    public ModelAndView get() {
        Collection<Profile> profiles = this.profileFacade.find();
        return defaultView(data(profiles));
    }

    @Override
    protected String defaultViewName() {
        return "profile-list";
    }

    private Object data(Collection<Profile> profiles) {
        Map<String, Object> data = new HashMap<>();
        data.put("profiles", profiles);
        return data;
    }
}
