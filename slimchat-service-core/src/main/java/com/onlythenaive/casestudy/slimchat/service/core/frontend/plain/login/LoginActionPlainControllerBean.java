package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;
import com.onlythenaive.casestudy.slimchat.service.core.security.token.TokenFacade;

@Controller
@RequestMapping("/ui/plain/login")
public class LoginActionPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private TokenFacade tokenFacade;

    @PostMapping
    public ModelAndView login(LoginFormInput form) {
        this.tokenFacade.createFromAccountLoginPair(form.getLoginKey(), form.getLoginSecret());
        return redirect("profiles/me");
    }
}
