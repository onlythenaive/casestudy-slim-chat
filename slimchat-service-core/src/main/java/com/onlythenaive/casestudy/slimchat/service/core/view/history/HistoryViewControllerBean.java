package com.onlythenaive.casestudy.slimchat.service.core.view.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.history.HistoryFacade;
import com.onlythenaive.casestudy.slimchat.service.core.view.shared.GenericViewControllerBean;

@Controller
@RequestMapping("/view/history")
public class HistoryViewControllerBean extends GenericViewControllerBean {

    @Autowired
    private HistoryFacade historyFacade;

    @PostMapping("/delete")
    public ModelAndView post(HistoryInvoiceForm form) {
        this.historyFacade.remove(form.getHistoryId());
        return redirectToView(form.getViewToRedirect());
    }

    @Override
    protected String defaultViewName() {
        return "history";
    }
}
