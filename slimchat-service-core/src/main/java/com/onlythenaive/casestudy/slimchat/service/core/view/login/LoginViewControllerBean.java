package com.onlythenaive.casestudy.slimchat.service.core.view.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.security.SecurityCredentials;
import com.onlythenaive.casestudy.slimchat.service.core.security.SecurityService;
import com.onlythenaive.casestudy.slimchat.service.core.view.generic.GenericViewControllerBean;

@Controller
@RequestMapping("/view/login")
public class LoginViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private SecurityService securityService;

    @GetMapping
    public ModelAndView get() {
        return view("login");
    }

    @PostMapping
    public ModelAndView post(SecurityCredentials credentials) {
        try {
            this.securityService.login(credentials);
            return redirect("/view/home");
        } catch (Exception e) {
            return loginFailed();
        }
    }

    private ModelAndView loginFailed() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("content", mistakeContent("Login attempt failed!"));
        return modelAndView;
    }

    private Object mistakeContent(String message) {
        Map<String, String> content = new HashMap<>();
        content.put("mistake", message);
        return content;
    }
}
