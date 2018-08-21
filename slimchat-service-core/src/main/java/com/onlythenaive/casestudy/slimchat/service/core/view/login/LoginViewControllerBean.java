package com.onlythenaive.casestudy.slimchat.service.core.view.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.security.SecurityService;
import com.onlythenaive.casestudy.slimchat.service.core.security.authentication.AuthenticationContext;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/login")
public class LoginViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AuthenticationContext authenticationContext;

    @GetMapping
    public ModelAndView get() {
        if (authenticationContext.getAuthentication() != null) {
            return redirectToView("home");
        }
        return defaultView();
    }

    @PostMapping
    public ModelAndView post(LoginFormData formData) {
        this.securityService.login(formData.getAccountName(), formData.getAccountPassword());
        return redirectToView("home");
    }

    @Override
    protected String defaultViewName() {
        return "login";
    }
}
