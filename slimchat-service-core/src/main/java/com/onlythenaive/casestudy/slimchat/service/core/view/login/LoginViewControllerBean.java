package com.onlythenaive.casestudy.slimchat.service.core.view.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.security.SecurityFacade;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/login")
public class LoginViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private SecurityFacade securityFacade;

    @GetMapping
    public ModelAndView get() {
        return isAuthenticated() ? redirectToView("home") : defaultView();
    }

    @PostMapping
    public ModelAndView post(LoginFormData data) {
        this.securityFacade.authenticateByAccountCredentials(data.getAccountName(), data.getAccountPassword());
        return redirectToView("home");
    }

    @Override
    protected String defaultViewName() {
        return "login";
    }
}
