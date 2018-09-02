package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.profile;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileFacade;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/profiles")
public class ProfileViewPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private ProfileFacade profileFacade;

    @GetMapping("/me")
    public ModelAndView showMyProfile() {
        return redirect("profiles/" + principalId());
    }

    @GetMapping("/{profileId}")
    public ModelAndView showProfileById(@PathVariable("profileId") String profileId) {
        Profile profile = this.profileFacade.getById(profileId);
        return render("profile-view", data(profile));
    }

    private Object data(Profile profile) {
        Map<String, Object> data = new HashMap<>();
        data.put("profile", profile);
        return data;
    }
}
