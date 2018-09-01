package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

public interface HistoryFacade {

    void deleteByGroupId(String groupId);

    void deleteByProfileId(String profileId);
}
