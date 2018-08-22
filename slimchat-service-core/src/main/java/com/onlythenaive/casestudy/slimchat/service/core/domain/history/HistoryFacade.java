package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

public interface HistoryFacade {

    History getHistoryById(String id);

    History clearHistoryById(String id);
}
