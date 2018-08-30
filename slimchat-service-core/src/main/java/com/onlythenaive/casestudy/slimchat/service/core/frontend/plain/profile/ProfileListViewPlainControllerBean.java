package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.profile;

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
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/profiles")
public class ProfileListViewPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private ProfileFacade profileFacade;

    @GetMapping
    public ModelAndView show() {
        Collection<Profile> profiles = this.profileFacade.findAll();
        return render("profile-list-view", data(profiles));
    }

    private Object data(Collection<Profile> profiles) {
        Map<String, Object> data = new HashMap<>();
        data.put("profiles", profiles);
        return data;
    }
}
