package com.onlythenaive.casestudy.slimchat.service.core.view.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.view.generic.GenericViewControllerBean;

@Controller
@RequestMapping("/view/chat")
public class ChatViewControllerBean extends GenericViewControllerBean {

    @GetMapping
    public ModelAndView get() {
        return view("chat");
    }
}
