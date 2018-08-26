package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.logout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.TokenFacade;

@Controller
@RequestMapping("/ui/plain/logout")
public class LogoutActionPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private TokenFacade tokenFacade;

    @PostMapping
    public ModelAndView logout() {
        this.tokenFacade.delete();
        return redirect("login");
    }
}
