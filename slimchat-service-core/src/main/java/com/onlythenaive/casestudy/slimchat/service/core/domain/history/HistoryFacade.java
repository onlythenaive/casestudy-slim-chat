package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

public interface HistoryFacade {

    History getById(String id);

    HistorySearchResult getSearchResult(HistorySearchInvoice searchInvoice);

    void clear(String id);
}
