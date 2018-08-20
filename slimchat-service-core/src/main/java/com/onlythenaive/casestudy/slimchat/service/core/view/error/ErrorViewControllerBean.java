package com.onlythenaive.casestudy.slimchat.service.core.view.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/error")
public class ErrorViewControllerBean extends GenericViewControllerBean {

    @GetMapping
    public ModelAndView get() {
        return defaultView(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected String defaultViewName() {
        return "error";
    }
}
