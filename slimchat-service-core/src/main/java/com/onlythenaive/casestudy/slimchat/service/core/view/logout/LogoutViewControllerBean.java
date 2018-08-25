package com.onlythenaive.casestudy.slimchat.service.core.view.logout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.security.token.TokenFacade;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/logout")
public class LogoutViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private TokenFacade tokenFacade;

    @PostMapping
    public ModelAndView post() {
        this.tokenFacade.delete();
        return redirectToView("login");
    }

    @Override
    protected String defaultViewName() {
        return "logout";
    }
}
