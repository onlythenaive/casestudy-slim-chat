package com.onlythenaive.casestudy.slimchat.service.core.view.registration;

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
@RequestMapping("/view/registration")
public class RegistrationViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private SecurityService securityService;

    @GetMapping
    public ModelAndView get() {
        return view("registration");
    }

    @PostMapping
    public ModelAndView post(SecurityCredentials credentials) {
        String password = credentials.getPassword();
        String passwordDuplicate = credentials.getPasswordDuplicate();
        if (!password.equals(passwordDuplicate)) {
            return passwordNotMatchDuplicate();
        }
        try {
            this.securityService.createAccount(credentials);
            return view("/registration-complete");
        } catch (Exception e) {
            return registrationFailed();
        }
    }

    private ModelAndView passwordNotMatchDuplicate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        modelAndView.addObject("content", mistakeContent("Passwords do not match!"));
        return modelAndView;
    }

    private ModelAndView registrationFailed() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        modelAndView.addObject("content", mistakeContent("Registration failed!"));
        return modelAndView;
    }

    private Object mistakeContent(String message) {
        Map<String, String> content = new HashMap<>();
        content.put("mistake", message);
        return content;
    }
}
