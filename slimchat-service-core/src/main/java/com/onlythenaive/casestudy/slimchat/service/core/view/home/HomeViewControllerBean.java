package com.onlythenaive.casestudy.slimchat.service.core.view.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/home")
public class HomeViewControllerBean extends GenericViewControllerBean {

    @GetMapping
    public ModelAndView get() {
        return defaultView();
    }

    @Override
    protected String defaultViewName() {
        return "home";
    }
}
