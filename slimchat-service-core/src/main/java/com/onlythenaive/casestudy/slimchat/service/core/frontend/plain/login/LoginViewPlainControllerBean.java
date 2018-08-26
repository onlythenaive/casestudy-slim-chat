package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/login")
public class LoginViewPlainControllerBean extends GenericPlainControllerBean {

    @GetMapping
    public ModelAndView show() {
        if (isAuthenticated()) {
            return redirect("profiles/me");
        }
        return render("login-form-view");
    }
}
