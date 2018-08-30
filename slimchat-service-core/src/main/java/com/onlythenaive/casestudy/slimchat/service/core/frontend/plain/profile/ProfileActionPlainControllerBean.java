package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.Profile;
import com.onlythenaive.casestudy.slimchat.service.core.domain.profile.ProfileFacade;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/profiles")
public class ProfileActionPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private ProfileFacade profileFacade;

    @PostMapping("/update")
    public ModelAndView update(ProfileFormInput form) {
        Profile profile = this.profileFacade.update(form.getId(), form.toUpdateInvoice());
        return redirect("profiles/" + profile.getId());
    }
}
