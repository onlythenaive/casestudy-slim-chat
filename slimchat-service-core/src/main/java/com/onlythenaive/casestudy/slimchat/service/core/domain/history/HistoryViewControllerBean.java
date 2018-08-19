package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.onlythenaive.casestudy.slimchat.service.core.generic.GenericComponentBean;
import com.onlythenaive.casestudy.slimchat.service.core.utility.view.ViewService;

@RestController
public class HistoryViewControllerBean extends GenericComponentBean implements HistoryViewController {

    @Autowired
    private ViewService viewService;

    @Override
    public String getHistory() {
        return this.viewService.renderView("history");
    }
}
