package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/error")
public class ErrorViewPlainControllerBean extends GenericPlainControllerBean {

    @GetMapping
    public ModelAndView get() {
        return render("error-view", null, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
