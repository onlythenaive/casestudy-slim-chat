package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.AccessLevel;
import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainAccessorBean;

/**
 * Chat history accessor implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class HistoryAccessorBean extends DomainAccessorBean<HistoryEntity> implements HistoryAccessor {

    @Override
    public HistoryEntity ensureAccess(AccessLevel level, HistoryEntity subject) {
        if (!subject.getOwnerId().equals(principalId())) {
            throw insufficientPrivileges();
        }
        return subject;
    }

    @Override
    protected String entityName() {
        return "history";
    }
}
