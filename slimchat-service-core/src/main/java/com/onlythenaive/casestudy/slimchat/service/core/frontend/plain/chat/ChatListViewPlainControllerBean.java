package com.onlythenaive.casestudy.slimchat.service.core.frontend.plain.chat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlythenaive.casestudy.slimchat.service.core.domain.history.History;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.HistoryFacade;
import com.onlythenaive.casestudy.slimchat.service.core.domain.history.HistorySearchInvoice;
import com.onlythenaive.casestudy.slimchat.service.core.frontend.shared.GenericPlainControllerBean;

@Controller
@RequestMapping("/ui/plain/chats")
public class ChatListViewPlainControllerBean extends GenericPlainControllerBean {

    @Autowired
    private HistoryFacade historyFacade;

    @GetMapping
    public ModelAndView show(HistorySearchInvoice searchInvoice) {
        Collection<History> histories = this.historyFacade.getSearchResult(searchInvoice).getItems();
        return renderChatListView(histories);
    }

    private ModelAndView renderChatListView(Collection<History> histories) {
        Map<String, Object> data = new HashMap<>();
        data.put("chats", histories);
        return render("chat-list-view", data);
    }
}
