package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.utility.persistence.GenericAccessorBean;

/**
 * Chat history accessor implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class HistoryAccessorBean extends GenericAccessorBean<HistoryEntity> implements HistoryAccessor {

    @Override
    public AccessLevel allowedAccessLevel(HistoryEntity subject) {
        if (subject.getOwnerId().equals(principalId())) {
            return AccessLevel.MANAGE;
        }
        return AccessLevel.BYPASS;
    }

    @Override
    protected String getEntityName() {
        return "history";
    }
}
