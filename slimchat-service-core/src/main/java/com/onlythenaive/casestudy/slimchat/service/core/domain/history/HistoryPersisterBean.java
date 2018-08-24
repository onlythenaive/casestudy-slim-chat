package com.onlythenaive.casestudy.slimchat.service.core.domain.history;

import org.springframework.stereotype.Service;

import com.onlythenaive.casestudy.slimchat.service.core.domain.shared.DomainPersisterBean;

/**
 * Chat history persister implementation.
 *
 * @author Ilia Gubarev
 */
@Service
public class HistoryPersisterBean extends DomainPersisterBean<HistoryEntity> implements HistoryPersister {

}
