package com.onlythenaive.casestudy.slimchat.service.core.frontend.shared;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexViewControllerBean extends GenericFrontendControllerBean {

    @GetMapping
    public ModelAndView show() {
        return redirect("ui/plain/profiles/me");
    }
}
