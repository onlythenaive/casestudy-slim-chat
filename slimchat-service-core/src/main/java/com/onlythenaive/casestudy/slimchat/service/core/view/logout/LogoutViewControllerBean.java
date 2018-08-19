package com.onlythenaive.casestudy.slimchat.service.core.view.logout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.security.SecurityService;
import com.onlythenaive.casestudy.slimchat.service.core.view.generic.GenericViewControllerBean;

@Controller
@RequestMapping("/view/logout")
public class LogoutViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private SecurityService securityService;

    @PostMapping
    public ModelAndView post() {
        this.securityService.logout();
        return redirect("/view/login");
    }
}
