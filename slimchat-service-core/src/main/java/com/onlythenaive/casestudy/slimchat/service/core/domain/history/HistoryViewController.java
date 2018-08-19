package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/view/history")
public interface HistoryViewController {

    @RequestMapping()
    String getHistory();
}
