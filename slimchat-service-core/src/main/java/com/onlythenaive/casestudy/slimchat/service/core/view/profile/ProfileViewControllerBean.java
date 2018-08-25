package com.onlythenaive.casestudy.slimchat.service.core.view.profile;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileFacade;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/profile")
public class ProfileViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private ProfileFacade profileFacade;

    @GetMapping("/me")
    public ModelAndView getCurrent() {
        String accountId = principalId();
        Profile profile = this.profileFacade.getById(accountId);
        return defaultView(data(profile, "profile/me"));
    }

    @GetMapping("/{accountName}")
    public ModelAndView getByNickname(@PathVariable("accountName") String accountName) {
        Profile profile = this.profileFacade.getByAccountName(accountName);
        return defaultView(data(profile, "profile/" + accountName));
    }

    @GetMapping("/id-{id}")
    public ModelAndView getById(@PathVariable("id") String id) {
        Profile profile = this.profileFacade.getById(id);
        return defaultView(data(profile, "profile/id-" + id));
    }

    @PostMapping("/update")
    public ModelAndView post(ProfileUpdateForm form) {
        this.profileFacade.update(form.getId(), form.toUpdateInvoice());
        return redirectToView(form.getViewToRedirect());
    }

    @Override
    protected String defaultViewName() {
        return "profile";
    }

    private Object data(Profile profile, String redirectView) {
        Map<String, Object> data = new HashMap<>();
        data.put("profile", profile);
        data.put("viewToRedirect", redirectView);
        return data;
    }
}
